package com.googy.openmovies.movie.domain.repository

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.repository.BaseRepository
import com.googy.openmovies.main.data.model.MovieList
import com.googy.openmovies.main.domain.model.MovieDomainEntity
import com.googy.openmovies.movie.domain.model.EpisodeDomainEntity
import com.googy.openmovies.movie.domain.model.SeasonDomainEntity

interface MovieDetailsRepository : BaseRepository {
    suspend fun getMovie(movieId: Int): EitherErrorOr.Right<MovieDomainEntity>
    suspend fun getEpisodes(imdbId: String): EitherErrorOr<List<SeasonDomainEntity>>
    suspend fun getLocalEpisodes(imdbId: String): EitherErrorOr.Right<List<SeasonDomainEntity>>
}