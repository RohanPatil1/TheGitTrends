package com.rohan.thegittrends.data.network

import com.rohan.thegittrends.data.models.GitRepoItem
import com.rohan.thegittrends.utils.Resource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getTrendingRepos(): Resource<List<GitRepoItem>> {
        return getResponse(
            request = { apiService.getTrendingRepo() },
            defaultErrorMsg = "Error occurred!"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMsg: String
    ): Resource<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Resource.success(result.body())
            } else {
                return Resource.error("Something went wrong", null)
            }

        } catch (e: Throwable) {
            if (e is IOException) {
                Resource.error("No internet connection", null)
            } else {
                Resource.error("Unknown Error ${e.message}", null)

            }
        }
    }


}