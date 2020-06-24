package br.com.moviesapp.data.api

import br.com.moviesapp.data.api.network_responses.MovieResponse
import retrofit2.http.GET

interface MoviesApiService {

    @GET("saga")
    suspend fun loadMovies(): List<MovieResponse>
}