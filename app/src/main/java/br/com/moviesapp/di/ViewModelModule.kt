package br.com.moviesapp.di

import br.com.moviesapp.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MoviesViewModel(get(), get(), get())
    }
}
