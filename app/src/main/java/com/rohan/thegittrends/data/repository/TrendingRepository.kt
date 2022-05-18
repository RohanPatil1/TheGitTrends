package com.rohan.thegittrends.data.repository

import com.rohan.thegittrends.data.db.TrendingDao
import com.rohan.thegittrends.data.models.GitRepoItem
import com.rohan.thegittrends.data.network.NetworkDataSource
import com.rohan.thegittrends.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingDao: TrendingDao,
    private val networkDataSource: NetworkDataSource

) {

    suspend fun getTrendingRepos(): Flow<Resource<List<GitRepoItem>>?> {
        return flow {

            emit(Resource.loading())
            val result = networkDataSource.getTrendingRepos()

            //If result isSuccess, save in Db
            if (result.status == Resource.Status.SUCCESS) {
                result.data?.let { it ->
                    trendingDao.deleteAll(it)
                    trendingDao.insert(it)
                }
                emit(result)

            } else {
                val cachedData = getTrendingReposFromDb()
                if (cachedData?.data.isNullOrEmpty()) {
                    emit(result)
                } else {
                    emit(getTrendingReposFromDb())
                }
            }

        }.flowOn(Dispatchers.IO)
    }

    private fun getTrendingReposFromDb(): Resource<List<GitRepoItem>>? =
        trendingDao.getTrendingRepo()?.let {
            Resource.success(data = it)
        }


//    fun getTrendingRepos(): Flow<Resource<List<GitRepoItem>?>> {
//        val repoData = trendingDao.getTrendingRepo()
//        if (repoData.isEmpty()) {
//            Log.d("MyTest", "getTrendingRepos: DB DATA WAS EMPTY")
//            return getTrendingReposFromApi()
//        }
//        Log.d("MyTest", "getTrendingRepos: DB DATA WAS NOT EMPTY")
//
//        return flow {
//            emit(Resource.Success(repoData))
//        }
//    }
//
//    private fun getTrendingReposFromApi() = result {
//        apiService.getTrendingRepo()
//    }

}