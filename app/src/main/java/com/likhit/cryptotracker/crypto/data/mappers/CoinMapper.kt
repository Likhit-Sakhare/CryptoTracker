package com.likhit.cryptotracker.crypto.data.mappers

import com.likhit.cryptotracker.crypto.data.dto.CoinDto
import com.likhit.cryptotracker.crypto.data.dto.CoinPriceDto
import com.likhit.cryptotracker.crypto.domain.Coin
import com.likhit.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        rank = rank,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )
}