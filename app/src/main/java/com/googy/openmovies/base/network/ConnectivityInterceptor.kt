package com.googy.openmovies.base.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ConnectivityInterceptor(private val networkHandler: NetworkHandler) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkHandler.isConnected) {
            throw SocketTimeoutException()
        }
        return chain.proceed(chain.request())
    }
}
