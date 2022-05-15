package com.rohan.thegittrends.utils


sealed class Resource<out T> {

    data class Success<out R>(val data: R?) : Resource<R>()
    data class Failure(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}


//sealed class Resource<T>(val data: T? = null, val message: String? = null) {
//
//    class Success<T>(data: T?) : Resource<T>(data = data)
//
//    class Loading<T>(message: String?) : Resource<T>()
//
//    class Error<T>(message: String?) : Resource<T>(message = message)
//
//}
