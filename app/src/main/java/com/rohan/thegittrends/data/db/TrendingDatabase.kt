package com.rohan.thegittrends.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rohan.thegittrends.data.models.GitRepoItem

@Database(entities = [GitRepoItem::class], version = 1, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class TrendingDatabase : RoomDatabase() {
    abstract fun getDao(): TrendingDao
}