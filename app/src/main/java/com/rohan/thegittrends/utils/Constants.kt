package com.rohan.thegittrends.utils

import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

object Constants {
    const val BASE_URL = "https://private-anon-43386a72f8-githubtrendingapi.apiary-mock.com/"
}

fun <T> result(call: suspend () -> Response<T>): kotlinx.coroutines.flow.Flow<Resource<T?>> = flow {
    emit(Resource.Loading)
    try {
        val c = call()
        c.let {
            if (c.isSuccessful) {
                emit(Resource.Success(it.body()))
            } else {

                c.errorBody()?.let { error ->
                    error.close()
                    emit(Resource.Failure(error.toString()))
                }
            }
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        if (t is IOException) {
            emit(Resource.Failure("No Internet Connection"))
        } else {
            emit(Resource.Failure(t.message.toString()))
        }
    }

}