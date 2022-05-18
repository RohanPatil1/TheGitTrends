package com.rohan.thegittrends.utils

import androidx.compose.ui.graphics.Color


object Constants {
    const val BASE_URL = "https://private-anon-43386a72f8-githubtrendingapi.apiary-mock.com/"
//    const val BASE_URL = "https://ghapi.huchen.dev/repositories/"
}

object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor(colorString))
    }
}