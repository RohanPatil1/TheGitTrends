package com.rohan.thegittrends.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trendingTable")
data class GitRepoItem(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltBy>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}