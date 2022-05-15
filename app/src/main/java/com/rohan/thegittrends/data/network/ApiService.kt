package com.rohan.thegittrends.data.network

import com.rohan.thegittrends.data.models.GitRepoItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("repositories")
    suspend fun getTrendingRepo(): Response<List<GitRepoItem>>

}