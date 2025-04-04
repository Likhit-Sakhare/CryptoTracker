package com.likhit.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.likhit.cryptotracker.crypto.presentation.utils.CoinUI

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUI> = emptyList(),
    val selectedCoin: CoinUI? = null
)
