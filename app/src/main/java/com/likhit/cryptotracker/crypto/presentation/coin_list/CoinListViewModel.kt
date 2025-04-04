package com.likhit.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.likhit.cryptotracker.core.domain.onError
import com.likhit.cryptotracker.core.domain.onSuccess
import com.likhit.cryptotracker.crypto.domain.repository.CoinRepository
import com.likhit.cryptotracker.crypto.presentation.coin_detail.DataPoint
import com.likhit.cryptotracker.crypto.presentation.utils.CoinUI
import com.likhit.cryptotracker.crypto.presentation.utils.toCoinUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinListViewModel(
    private val coinRepository: CoinRepository
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onCoinClick(coin: CoinUI) {
        _state.update {
            it.copy(
                selectedCoin = coin
            )
        }
        viewModelScope.launch {
            coinRepository.getCoinHistory(
                coinId = coin.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now()
            ).onSuccess { history ->
                val dataPoints = history
                    .sortedBy { it.dateTime }
                    .map {
                        DataPoint(
                            x = it.dateTime.hour.toFloat(),
                            y = it.priceUsd.toFloat(),
                            xLabel = DateTimeFormatter
                                .ofPattern("ha\nM/d")
                                .format(it.dateTime)
                        )
                    }
                _state.update {
                    it.copy(
                        selectedCoin = it.selectedCoin?.copy(
                            coinPriceHistory = dataPoints
                        )
                    )
                }
            }.onError { error ->
                _events.send(CoinListEvent.Error(error))
            }
        }
    }

    private fun loadCoins(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinRepository.getCoins().onSuccess { coins ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        coins = coins.map { coin ->
                            coin.toCoinUI()
                        }
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                _events.send(CoinListEvent.Error(error))
            }
        }
    }
}