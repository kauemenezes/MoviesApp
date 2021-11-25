package br.com.moviesapp.di

import br.com.moviesapp.domain.usecases.GetMovieUseCase
import br.com.moviesapp.domain.usecases.GetMoviesUseCase
import br.com.moviesapp.domain.usecases.LoadMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMovieUseCase(get()) }
    single { GetMoviesUseCase(get()) }
    single { LoadMoviesUseCase(get()) }
}