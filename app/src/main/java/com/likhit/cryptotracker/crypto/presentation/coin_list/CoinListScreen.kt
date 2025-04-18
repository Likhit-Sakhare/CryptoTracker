package com.likhit.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.likhit.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.likhit.cryptotracker.crypto.presentation.utils.CoinUI

@Composable
fun CoinListScreen(
    state: CoinListState,
    onCoinClick: (CoinUI) -> Unit,
    modifier: Modifier = Modifier
) {
    if(state.isLoading){
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins){ coin ->
                CoinListItem(
                    coin = coin,
                    onClick = {
                        onCoinClick(coin)
                    },
                    modifier = Modifier.fillParentMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}