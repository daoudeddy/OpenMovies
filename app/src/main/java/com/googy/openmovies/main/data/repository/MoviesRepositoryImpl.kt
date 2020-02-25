package com.googy.openmovies.main.data.repository

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.repository.safeApiCall
import com.googy.openmovies.main.data.service.MoviesApiService
import com.googy.openmovies.main.data.service.MoviesDaoService
import com.googy.openmovies.main.domain.model.MovieDomainEntity
import com.googy.openmovies.main.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesApiService: MoviesApiService,
    private val settingsRoomService: MoviesDaoService
) : MoviesRepository {
    override suspend fun getLocalMovies(type: String): EitherErrorOr<List<MovieDomainEntity>> {
        return EitherErrorOr.Right(
            settingsRoomService.getMovies(
                type,
                false
            ).map { it.toDomainEntity() })
    }

    override suspend fun getMovies(
        type: String,
        query: String
    ): EitherErrorOr<List<MovieDomainEntity>> {
        return safeApiCall(moviesApiService.getMovies(type, query)) {
            val newList = it.movieList.map { movie ->
                movie.search = query.isNotEmpty()
                movie.type = type
                movie.seeds = movie.popularity.replace("-", "").toLong()
                movie
            }.sortedByDescending { it.seeds }.take(50)
            settingsRoomService.insert(newList)
            settingsRoomService.delete(newList.map { it.id }, type)
            newList.map { item -> item.toDomainEntity() }
        }
    }
}

