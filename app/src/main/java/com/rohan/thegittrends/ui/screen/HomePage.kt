package com.rohan.thegittrends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rohan.thegittrends.ui.view_model.TrendingViewModel

@Composable
fun HomePage(viewModel: TrendingViewModel = hiltViewModel()) {
    val res = viewModel.repoList.value
    val isRefreshing by viewModel.isRefreshing



    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() },
    ) {

        if (res.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        if (res.error.isNotBlank()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = res.error
                )
                TextButton(onClick = { viewModel.refresh() }) {
                    Text(text = "Retry")
                }
            }
        }


        res.data?.let {
            LazyColumn {
                items(it) {
                    Text(text = it.name)
                }
            }
        }
    }


}