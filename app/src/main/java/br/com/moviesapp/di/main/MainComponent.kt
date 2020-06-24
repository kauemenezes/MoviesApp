package br.com.moviesapp.di.main

import br.com.moviesapp.ui.MainActivity
import br.com.moviesapp.ui.moviedetails.MovieDetailsFragment
import br.com.moviesapp.ui.movies.MoviesFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = [
        MainModule::class,
        MainViewModelModule::class
    ])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory{

        fun create(): MainComponent
    }

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}