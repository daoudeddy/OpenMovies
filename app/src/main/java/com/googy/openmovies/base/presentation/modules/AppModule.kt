package com.googy.openmovies.base.presentation.modules

import com.google.gson.Gson
import com.googy.openmovies.base.data.local.MoviesRoomDatabase
import com.googy.openmovies.base.network.provideRetrofit
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


fun getModules(): List<Module> {
    val appModule = module {
        single { Gson() }
        single { provideRetrofit() }
    }

    return listOf(
        appModule,
        databaseModule,
        repositoryModule,
        serviceModule,
        useCaseModule,
        viewModelModule
    )
}

