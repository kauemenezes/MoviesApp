package br.com.moviesapp.di.main

import br.com.moviesapp.data.api.MoviesApiService
import br.com.moviesapp.data.db.MoviesDatabase
import br.com.moviesapp.data.db.dao.MoviesDao
import br.com.moviesapp.data.repository.MoviesRepositoryImpl
import br.com.moviesapp.data.source.MovieDataSource
import br.com.moviesapp.di.IoDispatcher
import br.com.moviesapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher


@Module
object MainModule {

    @JvmStatic
    @MainScope
    @Provides
    fun provideMoviesDao(db: MoviesDatabase): MoviesDao {
        return db.moviesDao()
    }

    @JvmStatic
    @MainScope
    @Provides
    fun provideMovieDataSource(
        apiService: MoviesApiService,
        moviesDao: MoviesDao
    ): MovieDataSource {
        return MovieDataSource(apiService, moviesDao)
    }

    @JvmStatic
    @MainScope
    @Provides
    fun provideMoviesRepository(
        movieDataSource: MovieDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MoviesRepository {
        return MoviesRepositoryImpl(movieDataSource, ioDispatcher)
    }
}