package com.rohan.thegittrends.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rohan.thegittrends.ui.view_model.TrendingViewModel

@Composable
fun HomePage(viewModel: TrendingViewModel = hiltViewModel()) {
    val res = viewModel.repoList.value

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
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = res.error, modifier = Modifier
                    .align(Alignment.Center)
            )
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