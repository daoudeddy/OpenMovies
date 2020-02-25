package com.googy.openmovies.main.domain.model

import com.googy.openmovies.base.data.model.BaseDBEntity
import com.googy.openmovies.base.presentation.model.BaseUIEntity
import com.googy.openmovies.main.data.model.Items
import com.googy.openmovies.main.data.model.ItemsLanguage
import com.googy.openmovies.main.presentation.model.MovieUIEntity
import com.googy.openmovies.movie.presentation.model.MovieInfoUIEntity
import com.googy.openmovies.movie.presentation.model.TrailerUIEntity

//import io.objectbox.relation.ToMany

data class MovieDomainEntity(
    val actors: String,
    val description: String,
    val directors: String,
    val genres: List<String>,
    val movieId: Int,
    val imdb: String,
    val items: List<Items>,
    val items_lang: List<ItemsLanguage>,
    val popularity: String,
    val poster_big: String,
    val poster_med: String,
    val rating: Double,
    val runtime: Int,
    val title: String,
    val trailer: String,
    val writers: String,
    val year: Int
) : BaseDBEntity(movieId.toString()) {
    fun toUiEntity(): MovieUIEntity {
        return MovieUIEntity(
            actors,
            description,
            directors,
            genres,
            movieId,
            imdb,
            items,
            items_lang,
            popularity,
            poster_big,
            poster_med,
            rating,
            runtime,
            title,
            trailer,
            writers,
            year
        )
    }

    fun toUIEntities(): MutableList<BaseUIEntity> = mutableListOf<BaseUIEntity>().apply {
        if (trailer.isNotEmpty()) add(TrailerUIEntity(trailer))
        add(
            MovieInfoUIEntity(
                id.toString(),
                title,
                actors,
                directors,
                genres,
                poster_med,
                year,
                runtime,
                rating,
                description
            )
        )
        addAll(items.map { it.toUIEntity() })
    }
}