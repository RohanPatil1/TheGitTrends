package com.rohan.thegittrends.utils

import com.rohan.thegittrends.data.models.GitRepoItem

data class HomeStateHolder(
    val isLoading: Boolean = false,
    val data: List<GitRepoItem>? = null,
    val error: String = ""
)
