package com.googy.openmovies.base.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkHandler constructor(context: Context) {
    val isConnected= (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected ?: false
}