package com.googy.openmovies.movie.domain.model

import com.googy.openmovies.base.domain.model.BaseDomainEntity
import com.googy.openmovies.movie.presentation.model.SeasonUIEntity

data class SeasonDomainEntity(
    val season: String,
    val episodes: List<EpisodeDomainEntity>
) : BaseDomainEntity<SeasonUIEntity>() {
    fun toUiEntity(imdbId: String): SeasonUIEntity {
        return SeasonUIEntity(season, imdbId, episodes.map { it.toUiEntity() })
    }

    override fun toUiEntity(): SeasonUIEntity {
        return SeasonUIEntity(season, "", episodes.map { it.toUiEntity() })
    }
}