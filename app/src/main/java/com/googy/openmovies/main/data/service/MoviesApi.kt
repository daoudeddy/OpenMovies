package com.googy.openmovies.main.data.service

import com.googy.openmovies.main.data.model.MoviesDataEntity
import com.googy.openmovies.main.data.model.Season
import retrofit2.Call
import retrofit2.http.*

interface MoviesApi {
    @GET("{type}?sort=seeds&cb=&quality=720p,1080p,3d")
    fun getMovies(@Path("type") path: String, @Query("keywords") query: String): Call<MoviesDataEntity>

    @GET("show")
    fun getSeasons(@Query("imdb") imdbId: String): Call<Season>
}