package com.googy.openmovies.movie.presentation.model

import com.googy.openmovies.base.presentation.ViewHolderFactory
import com.googy.openmovies.base.presentation.`object`.Keys.SPAN_SIZE_SIX
import com.googy.openmovies.base.presentation.model.BaseUIEntity

data class MovieInfoUIEntity(
    val movieId: String,
    val title: String,
    val actors: String,
    val directors: String,
    val genres: List<String>,
    val poster: String,
    val year: Int,
    val runtime: Int,
    val rating: Double,
    val description: String
) : BaseUIEntity(SPAN_SIZE_SIX) {
    override fun getId(): String {
        return title + movieId
    }

    override fun equals(other: BaseUIEntity): Boolean {
        return other is MovieInfoUIEntity && movieId == other.movieId &&
                other.poster == poster && other.title == title
    }

    override fun getViewType(): Int {
        return ViewHolderFactory.ViewType.MOVIE_INFO
    }
}