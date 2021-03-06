package br.com.moviesapp

import android.app.Application
import br.com.moviesapp.di.AppComponent
import br.com.moviesapp.di.DaggerAppComponent

open class BaseApplication : Application() {

    open val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}