package com.googy.openmovies.base.presentation.modules

import android.app.Application
import androidx.room.Room
import com.googy.openmovies.base.data.local.MoviesRoomDatabase
import com.googy.openmovies.main.data.service.MoviesDao
import com.googy.openmovies.movie.data.service.MovieDetailDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideRoomDatabase(androidApplication()) }
    single { provideMoviesDao(get()) }
    single { provideMovieDao(get()) }
}

fun provideRoomDatabase(app: Application): MoviesRoomDatabase {
    return Room.databaseBuilder(
        app.applicationContext,
        MoviesRoomDatabase::class.java,
        "movies_database"
    ).fallbackToDestructiveMigration().build()
}

private fun provideMoviesDao(database: MoviesRoomDatabase): MoviesDao {
    return database.moviesDao()
}

private fun provideMovieDao(database: MoviesRoomDatabase): MovieDetailDao {
    return database.movieDao()
}

