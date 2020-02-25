/*
 * Created by Edd on 3/20/19 11:21 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 3/1/19 12:47 PM by Edd
 */

package com.googy.openmovies.base.domain.repository

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.BuildConfig
import org.koin.core.KoinComponent
import retrofit2.Call
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException

interface BaseRepository : KoinComponent {
    fun Exception.toResultError() = when (this) {
        is SocketTimeoutException, is SSLException -> Result.NetworkError
        else -> Result.ExceptionError(this)
    }
}


suspend inline fun <T : Any> BaseRepository.safeApiCall(call: Call<T>): EitherErrorOr<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.execute()
            val responseBody = response.body()
            if (!response.isSuccessful || responseBody == null)
                EitherErrorOr.Left(Result.ServerError(response.errorBody()?.string()))
            else EitherErrorOr.Right(responseBody)

        } catch (exception: Exception) {
            if (BuildConfig.DEBUG) exception.printStackTrace()
            EitherErrorOr.Left(exception.toResultError())
        }
    }
}

suspend inline fun <T : Any, R : Any> BaseRepository.safeApiCall(
    call: Call<T>,
    crossinline transformation: (T) -> R
): EitherErrorOr<R> {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.execute()
            val responseBody = response.body()
            if (!response.isSuccessful || responseBody == null)
                EitherErrorOr.Left(Result.ServerError(response.errorBody()?.string()))
            else EitherErrorOr.Right(transformation(responseBody))

        } catch (exception: Exception) {
            if (BuildConfig.DEBUG) exception.printStackTrace()
            EitherErrorOr.Left(exception.toResultError())
        }
    }
}