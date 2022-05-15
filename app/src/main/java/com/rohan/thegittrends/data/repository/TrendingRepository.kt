package com.rohan.thegittrends.data.repository

import com.rohan.thegittrends.data.network.ApiService

import javax.inject.Inject

class TrendingRepository @Inject constructor(private val apiService: ApiService)