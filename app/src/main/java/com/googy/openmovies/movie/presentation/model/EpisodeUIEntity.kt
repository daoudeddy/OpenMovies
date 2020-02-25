package com.googy.openmovies.movie.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX
import com.googy.openmovies.base.presentation.model.BaseUIEntity

data class EpisodeUIEntity(
    val air_time: String,
    val episode: String,
    val episodeId: String,
    val imdb: String,
    val items: List<TorrentUIEntity>,
    val movie_id: String,
    val runtime: Int,
    val season: String,
    val synopsis: String,
    val title: String
) : BaseUIEntity(SPAN_SIZE_SIX) {
    override fun getId(): String {
        return episodeId
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is EpisodeUIEntity && other.episodeId == episodeId
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.EPISODES
    }

}