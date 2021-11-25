package br.com.moviesapp

import android.app.Application
import br.com.moviesapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            modules(listOf(networkModule, viewModelModule, databaseModule, useCaseModule))
        }
    }
}