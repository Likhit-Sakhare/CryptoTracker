package com.likhit.cryptotracker.crypto.domain.repository

import com.likhit.cryptotracker.core.domain.NetworkError
import com.likhit.cryptotracker.core.domain.Result
import com.likhit.cryptotracker.crypto.domain.Coin
import com.likhit.cryptotracker.crypto.domain.CoinPrice
import java.time.ZonedDateTime

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}