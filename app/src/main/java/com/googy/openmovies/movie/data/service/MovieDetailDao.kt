package com.googy.openmovies.movie.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.googy.openmovies.main.data.model.Episodes
import com.googy.openmovies.main.data.model.MovieList

@Dao
interface MovieDetailDao {
    @Query("SELECT * from movies Where id = :movieId")
    fun getMovie(movieId: Int): MovieList

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episodes: List<Episodes>)

    @Query("SELECT * from episodes Where imdb  LIKE '%' || :imdbId || '%'")
    fun getEpisodes(imdbId: String): List<Episodes>
}