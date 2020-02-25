package com.googy.openmovies.base.presentation.modules

import com.googy.openmovies.main.domain.usecase.MoviesUseCase
import com.googy.openmovies.movie.domain.usecase.MovieDetailsUseCase
import com.googy.openmovies.movie.domain.usecase.SeriesSeasonsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MoviesUseCase(get()) }
    single { MovieDetailsUseCase(get()) }
    single { SeriesSeasonsUseCase(get()) }

}