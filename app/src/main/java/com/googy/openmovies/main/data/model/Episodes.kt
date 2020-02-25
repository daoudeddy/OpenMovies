package com.googy.openmovies.main.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.googy.openmovies.base.data.model.BaseDataEntity
import com.googy.openmovies.movie.domain.model.EpisodeDomainEntity
import com.googy.openmovies.movie.presentation.model.EpisodeUIEntity

@Entity
data class Episodes(
    val air_time: String,
    val episode: String,
    @PrimaryKey
    val id: String,
    val imdb: String,
    val items: List<Items>,
    val movie_id: String,
    val runtime: Int,
    val season: String,
    val synopsis: String,
    val title: String
) : BaseDataEntity<EpisodeUIEntity, EpisodeDomainEntity>() {
    override fun toDomainEntity(): EpisodeDomainEntity {
        return EpisodeDomainEntity(
            air_time,
            episode,
            id,
            imdb,
            items,
            movie_id,
            runtime,
            season,
            synopsis,
            title
        )
    }
}