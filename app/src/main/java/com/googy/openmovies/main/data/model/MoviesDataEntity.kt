package com.googy.openmovies.main.data.model

import com.google.gson.annotations.SerializedName

data class MoviesDataEntity(
    @SerializedName("MovieList")
    val movieList: List<MovieList>
)