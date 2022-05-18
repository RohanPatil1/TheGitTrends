package com.rohan.thegittrends.ui.screen

import android.util.Log
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
import com.rohan.thegittrends.ui.widgets.MainAppbar
import com.rohan.thegittrends.ui.widgets.SearchBarState

@Composable
fun HomePage(viewModel: TrendingViewModel = hiltViewModel()) {
    val res = viewModel.repoList.value
    val isRefreshing by viewModel.isRefreshing

    val searchBarState by viewModel.searchBarState
    val searchTextState by viewModel.searchTextState


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

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            MainAppbar(
                searchBarState = searchBarState,
                searchText = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(text = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchBarState(searchBarState = SearchBarState.CLOSED)
                    viewModel.resetSearch()
                },
                onSearchClicked = {
                    viewModel.onSearch(it)
                    Log.d("Search", it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchBarState(searchBarState = SearchBarState.OPENED)
                }
            )

            res.data?.let {
                LazyColumn {
                    items(it) {
                        Text(text = it.name)
                    }
                }
            }
        }


    }


}

