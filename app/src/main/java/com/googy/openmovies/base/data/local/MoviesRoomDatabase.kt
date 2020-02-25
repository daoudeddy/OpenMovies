package com.googy.openmovies.base.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.googy.openmovies.main.data.converter.ItemTypeConverters
import com.googy.openmovies.main.data.converter.StringTypeConverters
import com.googy.openmovies.main.data.model.Episodes
import com.googy.openmovies.main.data.model.MovieList
import com.googy.openmovies.main.data.service.MoviesDao
import com.googy.openmovies.movie.data.service.MovieDetailDao

@TypeConverters(ItemTypeConverters::class, StringTypeConverters::class)
@Database(entities = [MovieList::class, Episodes::class], version = 3, exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun movieDao(): MovieDetailDao
}