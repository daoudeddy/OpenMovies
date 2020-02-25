/*
 * Created by Edd on 3/19/19 1:07 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 3/19/19 12:32 PM by Edd
 */

package com.googy.openmovies.base.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.googy.openmovies.main.data.model.Season
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.ukfrnlge.xyz/"
private const val CONNECT_TIMEOUT = 30000L
private const val READ_TIMEOUT = 30000L
private const val WRITE_TIMEOUT = 90000L

fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val httpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(logging)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)

    return httpClientBuilder.build()
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(provideGsonConverterFactory())
        .build()
}

fun provideGsonConverterFactory(): GsonConverterFactory {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(Season::class.java, SeasonDeserializer())
    return GsonConverterFactory.create(gsonBuilder.create())
}



