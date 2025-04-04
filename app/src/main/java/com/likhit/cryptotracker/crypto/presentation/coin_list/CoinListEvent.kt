package com.likhit.cryptotracker.crypto.presentation.coin_list

import com.likhit.cryptotracker.core.domain.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}