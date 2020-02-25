/*
 * Created by Edd on 3/20/19 11:19 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 1/30/19 1:15 PM by Edd
 */

package com.googy.openmovies.base.domain.model


sealed class Result<out T> {

    object Pending : Result<Nothing>()

    data class Success<T> constructor(val data: T) : Result<T>()

    data class ServerError constructor(val message: String?) : Error()
    data class IOError constructor(val message: String) : Error()
    object NetworkError : Error()
    object ApiError : Error()

    data class ExceptionError constructor(val exception: kotlin.Exception) : Error()
    data class Exception constructor(val throwable: Throwable) : Error()

    open class Error : Result<Nothing>()

}
