package com.likhit.cryptotracker.crypto.data.repository

import com.likhit.cryptotracker.core.data.constructUrl
import com.likhit.cryptotracker.core.data.safeCall
import com.likhit.cryptotracker.core.domain.NetworkError
import com.likhit.cryptotracker.core.domain.Result
import com.likhit.cryptotracker.core.domain.map
import com.likhit.cryptotracker.crypto.data.dto.CoinHistoryDto
import com.likhit.cryptotracker.crypto.data.dto.CoinResponseDto
import com.likhit.cryptotracker.crypto.data.mappers.toCoin
import com.likhit.cryptotracker.crypto.data.mappers.toCoinPrice
import com.likhit.cryptotracker.crypto.domain.Coin
import com.likhit.cryptotracker.crypto.domain.CoinPrice
import com.likhit.cryptotracker.crypto.domain.repository.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class CoinRepositoryImpl(
    private val httpClient: HttpClient
): CoinRepository {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ){
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}