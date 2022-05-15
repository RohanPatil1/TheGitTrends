package com.rohan.thegittrends.data.repository

import com.rohan.thegittrends.data.network.ApiService
import com.rohan.thegittrends.utils.result

import javax.inject.Inject

class TrendingRepository @Inject constructor(private val apiService: ApiService) {

    fun getTrendingRepos() = result {
        apiService.getTrendingRepo()
    }

}