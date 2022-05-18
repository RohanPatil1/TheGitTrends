package com.rohan.thegittrends.data.di

import android.app.Application
import androidx.room.Room
import com.rohan.thegittrends.data.db.TrendingDao
import com.rohan.thegittrends.data.db.TrendingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(application: Application): TrendingDatabase =
        Room.databaseBuilder(application, TrendingDatabase::class.java, "trendingDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(trendingDatabase: TrendingDatabase): TrendingDao = trendingDatabase.getDao()

}