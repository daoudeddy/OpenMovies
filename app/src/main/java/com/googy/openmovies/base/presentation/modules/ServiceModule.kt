package com.googy.openmovies.base.presentation.modules

import com.googy.openmovies.main.data.service.MoviesApiService
import com.googy.openmovies.main.data.service.MoviesDaoService
import com.googy.openmovies.movie.data.service.MovieDetailDaoService
import org.koin.dsl.module

val serviceModule = module {
    single { MoviesDaoService(get()) }
    single { MoviesApiService(get()) }
    single { MovieDetailDaoService(get()) }
}