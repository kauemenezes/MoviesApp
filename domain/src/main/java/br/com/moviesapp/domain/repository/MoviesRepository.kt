package br.com.moviesapp.domain.repository

import br.com.moviesapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun loadMovies()

    fun getMovies(): Flow<List<Movie>>

    fun getMovie(movieId: String): Flow<Movie>
}