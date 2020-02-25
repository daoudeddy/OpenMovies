package com.googy.openmovies.base.presentation.modules

import com.googy.openmovies.main.data.repository.MoviesRepositoryImpl
import com.googy.openmovies.main.domain.repository.MoviesRepository
import com.googy.openmovies.movie.data.repository.MovieDetailsRepositoryImpl
import com.googy.openmovies.movie.domain.repository.MovieDetailsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get()) }
}

