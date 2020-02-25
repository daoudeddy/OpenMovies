package com.googy.openmovies.movie.data.repository

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.repository.safeApiCall
import com.googy.openmovies.main.data.model.MovieList
import com.googy.openmovies.main.data.model.Season
import com.googy.openmovies.main.data.service.MoviesApiService
import com.googy.openmovies.main.domain.model.MovieDomainEntity
import com.googy.openmovies.movie.data.service.MovieDetailDaoService
import com.googy.openmovies.movie.domain.model.SeasonDomainEntity
import com.googy.openmovies.movie.domain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImpl(
    private val movieDetailDaoService: MovieDetailDaoService,
    private val moviesApiService: MoviesApiService
) : MovieDetailsRepository {
    override suspend fun getLocalEpisodes(imdbId: String): EitherErrorOr.Right<List<SeasonDomainEntity>> {
        return EitherErrorOr.Right(Season(movieDetailDaoService.getEpisodes(imdbId)).toDomainEntity())
    }

    override suspend fun getEpisodes(imdbId: String): EitherErrorOr<List<SeasonDomainEntity>> {
        return safeApiCall(moviesApiService.getSeasons(imdbId)) {
            movieDetailDaoService.insert(it.episodes)
            it.toDomainEntity()
        }
    }


    override suspend fun getMovie(movieId: Int): EitherErrorOr.Right<MovieDomainEntity> {
        return EitherErrorOr.Right(movieDetailDaoService.getMovie(movieId).toDomainEntity())
    }
}