package com.googy.openmovies.main.data.service

import com.googy.openmovies.main.data.model.MoviesDataEntity
import com.googy.openmovies.main.data.model.Season
import retrofit2.Call
import retrofit2.Retrofit

class MoviesApiService(private val retrofit: Retrofit) : MoviesApi {
    override fun getSeasons(imdbId: String): Call<Season> {
        return moviesApi.getSeasons(imdbId)
    }

    override fun getMovies(type: String, query: String): Call<MoviesDataEntity> {
        return moviesApi.getMovies(type, query)
    }

    private val moviesApi: MoviesApi by lazy { retrofit.create(MoviesApi::class.java) }

}