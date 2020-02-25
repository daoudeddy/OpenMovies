package com.googy.openmovies.base

import android.app.Application
//import com.googy.openmovies.base.data.local.ObjectBox
import com.googy.openmovies.base.presentation.modules.getModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

//        ObjectBox.init(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(getModules())
        }
    }
}