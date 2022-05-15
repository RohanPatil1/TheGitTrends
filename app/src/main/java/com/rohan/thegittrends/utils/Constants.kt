package com.rohan.thegittrends.utils

import kotlinx.coroutines.flow.flow
import retrofit2.Response

object Constants {
    const val BASE_URL = "https://private-anon-43386a72f8-githubtrendingapi.apiary-mock.com/"
}

fun <T> result(call: suspend () -> Response<T>): kotlinx.coroutines.flow.Flow<Resource<T?>> = flow {


}