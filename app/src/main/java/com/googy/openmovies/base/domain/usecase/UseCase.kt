package com.googy.openmovies.base.domain.usecase

import android.util.Log
import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.model.Result
import kotlinx.coroutines.*

abstract class EitherUserCaseFolded<Type : Any, ParamsWrapper> {

    open val backgroundContext get() = Dispatchers.IO

    private fun getHandler(onFailure: (errorResult: Result.Error) -> Unit) =
        CoroutineExceptionHandler { _, exception ->
            onFailure(Result.Exception(exception))
        }

    var parent = SupervisorJob()
    private val foregroundContext = Dispatchers.Main
    private lateinit var parentScope: CoroutineScope

    abstract suspend fun run(params: ParamsWrapper): EitherErrorOr<Type>

    operator fun invoke(
        params: ParamsWrapper,
        onSuccess: (type: Type) -> Unit = {},
        onFailure: (errorResult: Result.Error) -> Unit = {},
        onCompleted: () -> Unit = {},
        parentScope: CoroutineScope
    ) {

        this.parentScope = parentScope
        parent.cancel()
        parent = SupervisorJob()

        this.parentScope.launch(foregroundContext + parent + getHandler(onFailure)) {
            val either = withContext(backgroundContext) { run(params) }
            either.fold({ errorResult -> onFailure(errorResult) }, { type -> onSuccess(type) })
            onCompleted()
        }
    }

    fun <T> async(func: suspend () -> T): Deferred<T> = this.parentScope.async(Dispatchers.IO) {
        func.invoke()
    }

    fun cancel() {
        parent.cancel()
    }

    fun getParentScope() = parentScope

}

object None



