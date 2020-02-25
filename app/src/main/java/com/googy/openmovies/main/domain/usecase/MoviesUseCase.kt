package com.googy.openmovies.main.domain.usecase

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.model.Result
import com.googy.openmovies.base.domain.usecase.EitherUserCaseFolded
import com.googy.openmovies.main.domain.model.MovieDomainEntity
import com.googy.openmovies.main.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope

class MoviesUseCase(
    private val moviesRepository: MoviesRepository
) : EitherUserCaseFolded<List<MovieDomainEntity>, MoviesUseCase.Params>() {

    override suspend fun run(params: Params): EitherErrorOr<List<MovieDomainEntity>> {
        return when {
            params.query == null -> moviesRepository.getLocalMovies(params.type)
            else -> moviesRepository.getMovies(params.type, params.query)
        }
    }

    data class Params(val type: String, val query: String?)

    fun getMoviesLiveData(
        type: String,
        onSuccess: (type: List<MovieDomainEntity>) -> Unit = {},
        onFailure: (errorResult: Result.Error) -> Unit = {},
        onCompleted: () -> Unit = {},
        parentScope: CoroutineScope
    ) = invoke(Params(type, null), onSuccess, onFailure, onCompleted, parentScope)
}