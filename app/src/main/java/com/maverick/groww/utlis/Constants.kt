package com.maverick.groww.utlis

import android.content.Context
import android.net.ConnectivityManager

object Constants {
    const val BASE_URL = "https://www.alphavantage.co/"
//    const val API_KEY = "TAAETJT2QUCNZCWP"
//    const val API_KEY = "X3WIHXV3XHMUGI0S"
    const val API_KEY = "7W3T10CF823B85UD"

    fun hasNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}