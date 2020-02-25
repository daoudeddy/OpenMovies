package com.googy.openmovies.main.data.model

import androidx.room.*
import com.googy.openmovies.main.domain.model.MovieDomainEntity

@Entity(tableName = "movies")
data class MovieList(
    @PrimaryKey
    val id: Int,
    val actors: String,
    val description: String,
    val directors: String,
    val genres: List<String>,
    val imdb: String,
    val items: List<Items>,
    var popularity: String,
    var seeds: Long,
    val poster_big: String,
    val poster_med: String,
    val rating: Double,
    val runtime: Int,
    val title: String,
    val trailer: String,
    val writers: String,
    val year: Int,
    var type: String,
    var search: Boolean
) {
    fun toDomainEntity(): MovieDomainEntity {
        return MovieDomainEntity(
            actors,
            description,
            directors,
            genres,
            id,
            imdb,
            items,
            arrayListOf(),
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
}