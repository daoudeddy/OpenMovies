package com.googy.openmovies.main.domain.repository

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.repository.BaseRepository
import com.googy.openmovies.main.data.model.MovieList
import com.googy.openmovies.main.domain.model.MovieDomainEntity

interface MoviesRepository : BaseRepository {
    suspend fun getMovies(type: String, query: String): EitherErrorOr<List<MovieDomainEntity>>
    suspend fun getLocalMovies(type: String): EitherErrorOr<List<MovieDomainEntity>>
}
