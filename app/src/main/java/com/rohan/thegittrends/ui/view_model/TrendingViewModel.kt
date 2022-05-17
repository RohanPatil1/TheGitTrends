package com.rohan.thegittrends.ui.view_model


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohan.thegittrends.data.repository.TrendingRepository
import com.rohan.thegittrends.utils.HomeStateHolder
import com.rohan.thegittrends.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val trendingRepository: TrendingRepository) :
    ViewModel() {

    val repoList = mutableStateOf(HomeStateHolder())
    val isRefreshing = mutableStateOf(false)

    init {
        getTrendingRepos()
    }

    private fun getTrendingRepos() {
        viewModelScope.launch {
            trendingRepository.getTrendingRepos().collect {
                when (it) {
                    is Resource.Loading -> {
                        repoList.value = HomeStateHolder(isLoading = true)
                    }
                    is Resource.Success -> {
                        repoList.value = HomeStateHolder(data = it.data)
                    }
                    is Resource.Failure -> {
                        repoList.value = HomeStateHolder(error = it.message)
                    }
                }
                if (isRefreshing.value) {
                    isRefreshing.value = false
                }
            }
        }
    }

    fun refresh() {
        isRefreshing.value = true
        getTrendingRepos()


        Log.d("Tag", "OnREfreshed Called")
    }
}