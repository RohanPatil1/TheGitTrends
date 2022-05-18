package com.rohan.thegittrends.ui.view_model


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohan.thegittrends.data.repository.TrendingRepository
import com.rohan.thegittrends.ui.widgets.SearchBarState
import com.rohan.thegittrends.utils.HomeStateHolder
import com.rohan.thegittrends.utils.Resource.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val trendingRepository: TrendingRepository) :
    ViewModel() {


    val repoList = mutableStateOf(HomeStateHolder())
    val isRefreshing = mutableStateOf(false)

    //BackUp data for search reset
    var tempRepoList = HomeStateHolder()

    //Search State utils
    private val _searchBarState: MutableState<SearchBarState> =
        mutableStateOf(value = SearchBarState.CLOSED)
    val searchBarState = _searchBarState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState = _searchTextState


    init {
        getTrendingRepos()
    }

    fun onSearch(str: String) {

        val data = repoList.value.data!!.filter {
            it.name.contains(str)
        }
        repoList.value = HomeStateHolder(data = data)
        Log.d("Search", "onSearch: Value Updated")
    }

    fun resetSearch() {
        repoList.value = tempRepoList
    }

    fun updateSearchBarState(searchBarState: SearchBarState) {
        _searchBarState.value = searchBarState
    }

    fun updateSearchTextState(text: String) {
        _searchTextState.value = text
    }

    private fun getTrendingRepos() {

        viewModelScope.launch {
            trendingRepository.getTrendingRepos().collect {
                when (it?.status) {
                    Status.SUCCESS -> {
                        repoList.value = HomeStateHolder(data = it.data)
                        tempRepoList = HomeStateHolder(data = it.data)

                    }
                    Status.LOADING -> {
                        repoList.value = HomeStateHolder(isLoading = true)
                    }
                    Status.ERROR -> {
                        repoList.value = HomeStateHolder(error = it.message.toString())
                    }
                }
                if (isRefreshing.value) {
                    isRefreshing.value = false
                }
            }
        }
    }

//    private fun getTrendingReposFromApi() {
//        viewModelScope.launch {
//            trendingRepository.getTrendingRepos().collect {
//                when (it) {
//                    is Resource.Loading -> {
//                        repoList.value = HomeStateHolder(isLoading = true)
//                    }
//                    is Resource.Success -> {
//                        repoList.value = HomeStateHolder(data = it.data)
//                        tempRepoList = HomeStateHolder(data = it.data)
//                    }
//                    is Resource.Failure -> {
//                        repoList.value = HomeStateHolder(error = it.message)
//                    }
//                }
//                if (isRefreshing.value) {
//                    isRefreshing.value = false
//                }
//            }
//        }
//    }

    //PullToRefresh Callback
    fun refresh() {
        isRefreshing.value = true
        getTrendingRepos()


        Log.d("Tag", "OnREfreshed Called")
    }


}