package com.rohan.thegittrends.ui.view_model


import androidx.lifecycle.ViewModel

import com.rohan.thegittrends.data.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val trendingRepository: TrendingRepository) :
    ViewModel()