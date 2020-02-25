package com.googy.openmovies.base.domain.model
sealed class EitherErrorOr<out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left(val a: Result.Error) : EitherErrorOr<Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : EitherErrorOr<R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left

    fun left(a: Result.Error) = Left(a)
    fun <R> right(b: R) = Right(b)

    inline fun fold(onFailure: (Result.Error) -> Any = {}, onSuccess: (R) -> Any = {}) {
        when (this) {
            is Left -> onFailure(a)
            is Right -> onSuccess(b)
        }
    }
}