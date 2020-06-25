package br.com.moviesapp.di.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.moviesapp.di.main.keys.MainViewModelKey
import br.com.moviesapp.ui.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel
}