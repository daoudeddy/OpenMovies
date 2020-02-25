package com.googy.openmovies.movie.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX
import com.googy.openmovies.base.presentation.model.BaseUIEntity

data class SeasonUIEntity(
    val season: String,
    val imdbId: String,
    val episodes: List<EpisodeUIEntity>
) : BaseUIEntity(SPAN_SIZE_SIX) {
    override fun getId(): String {
        return season
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is SeasonUIEntity && season == other.season
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.SEASONS
    }
}