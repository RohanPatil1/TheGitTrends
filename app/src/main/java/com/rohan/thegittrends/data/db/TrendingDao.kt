package com.rohan.thegittrends.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rohan.thegittrends.data.models.GitRepoItem

@Dao
interface TrendingDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(gitRepoItems: List<GitRepoItem>)

    @Query("SELECT * FROM trendingTable")
    fun getTrendingRepo(): List<GitRepoItem>?

    @Delete
    fun deleteAll(gitRepoItems: List<GitRepoItem>)

}