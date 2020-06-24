package br.com.moviesapp.data.source

import br.com.moviesapp.data.api.MoviesApiService
import br.com.moviesapp.data.db.dao.MoviesDao
import br.com.moviesapp.data.mappers.toDomain
import br.com.moviesapp.data.mappers.toEntity
import br.com.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val apiService: MoviesApiService,
    private val moviesDao: MoviesDao
) {

    suspend fun loadMovies() {
        val moviesListResponse = apiService.loadMovies()
        moviesDao.clearAndInsert(
            moviesListResponse.toEntity()
        )
    }

    fun getMovies(): Flow<List<Movie>> {
        return moviesDao.getMovies().map {
            it.toDomain()
        }
    }

    fun getMovie(movieId: String): Flow<Movie> {
        return moviesDao.getMovieDistinctUntilChanged(movieId).map {
            it.toDomain()
        }
    }
}