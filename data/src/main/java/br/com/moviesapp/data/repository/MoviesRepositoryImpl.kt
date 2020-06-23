package br.com.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import br.com.moviesapp.data.source.MovieDataSource
import br.com.moviesapp.domain.models.Movie
import br.com.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
): MoviesRepository {

    override suspend fun loadMovies() {
        withContext(Dispatchers.IO) {
            movieDataSource.loadMovies()
        }
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDataSource.getMovies()
    }

    override fun getMovie(movieId: String): Flow<Movie> {
        return movieDataSource.getMovie(movieId)
    }
}