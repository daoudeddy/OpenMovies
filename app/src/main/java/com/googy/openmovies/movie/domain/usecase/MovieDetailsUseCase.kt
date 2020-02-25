package com.googy.openmovies.movie.domain.usecase

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.usecase.EitherUserCaseFolded
import com.googy.openmovies.main.data.model.MovieList
import com.googy.openmovies.main.domain.model.MovieDomainEntity
import com.googy.openmovies.movie.domain.repository.MovieDetailsRepository

class MovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository
) : EitherUserCaseFolded<MovieDomainEntity, MovieDetailsUseCase.Params>() {
    override suspend fun run(params: Params): EitherErrorOr<MovieDomainEntity> {
        return movieDetailsRepository.getMovie(params.movieId)
    }

    data class Params(val movieId: Int)
}