package com.googy.openmovies.movie.domain.model

import com.googy.openmovies.base.domain.model.BaseDomainEntity
import com.googy.openmovies.main.data.model.Items
import com.googy.openmovies.main.data.model.ItemsLanguage
import com.googy.openmovies.movie.presentation.model.EpisodeUIEntity

data class EpisodeDomainEntity(
    val air_time: String,
    val episode: String,
    val id: String,
    val imdb: String,
    val items: List<Items>,
    val movie_id: String,
    val runtime: Int,
    val season: String,
    val synopsis: String,
    val title: String
) : BaseDomainEntity<EpisodeUIEntity>() {
    override fun toUiEntity(): EpisodeUIEntity {
        return EpisodeUIEntity(
            air_time,
            episode,
            id,
            imdb,
            items.map { it.toUIEntity() },
            movie_id,
            runtime,
            season,
            synopsis,
            title
        )
    }

}