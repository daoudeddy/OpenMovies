package com.googy.openmovies.movie.domain.usecase

import com.googy.openmovies.base.domain.model.EitherErrorOr
import com.googy.openmovies.base.domain.usecase.EitherUserCaseFolded
import com.googy.openmovies.movie.domain.model.EpisodeDomainEntity
import com.googy.openmovies.movie.domain.model.SeasonDomainEntity
import com.googy.openmovies.movie.domain.repository.MovieDetailsRepository


class SeriesSeasonsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository
) : EitherUserCaseFolded<List<SeasonDomainEntity>, SeriesSeasonsUseCase.Params>() {
    override suspend fun run(params: Params): EitherErrorOr<List<SeasonDomainEntity>> {
        return if (params.local) movieDetailsRepository.getLocalEpisodes(params.imdbId)
        else movieDetailsRepository.getEpisodes(params.imdbId)
    }

    data class Params(val imdbId: String, val local: Boolean)
}