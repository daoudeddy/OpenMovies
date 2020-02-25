package com.googy.openmovies.main.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.googy.openmovies.main.data.model.MovieList

@Dao
interface MoviesDao {

    @Query("SELECT * from movies Where search=:search AND type=:type ORDER BY seeds DESC LIMIT 50")
    fun getMovies(type: String, search: Boolean): List<MovieList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieList>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Query("DELETE FROM movies WHERE id NOT IN (:ids) AND type=:type")
    fun delete(ids: List<Int>, type: String)
}