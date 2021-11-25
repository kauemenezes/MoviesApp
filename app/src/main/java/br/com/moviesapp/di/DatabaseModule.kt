package br.com.moviesapp.di

import androidx.room.Room
import br.com.moviesapp.data.db.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room
            .databaseBuilder(androidContext(), MoviesDatabase::class.java, MoviesDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    single {
        get<MoviesDatabase>().moviesDao()
    }
}