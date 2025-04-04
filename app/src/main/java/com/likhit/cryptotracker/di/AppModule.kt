package com.likhit.cryptotracker.di

import com.likhit.cryptotracker.core.data.HttpClientFactory
import com.likhit.cryptotracker.crypto.data.repository.CoinRepositoryImpl
import com.likhit.cryptotracker.crypto.domain.repository.CoinRepository
import com.likhit.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }
    singleOf(::CoinRepositoryImpl).bind<CoinRepository>()
    viewModelOf(::CoinListViewModel)
}