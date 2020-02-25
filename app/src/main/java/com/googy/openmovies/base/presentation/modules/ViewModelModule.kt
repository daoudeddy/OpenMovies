package com.googy.openmovies.base.presentation.modules

import com.googy.openmovies.main.presentation.viewmodel.MainViewModel
import com.googy.openmovies.movie.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieViewModel(get(), get()) }
}
