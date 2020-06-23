package br.com.moviesapp.data.api

import br.com.moviesapp.data.api.network_responses.MoviesListResponse
import retrofit2.http.GET

interface MoviesApiService {

    @GET("saga")
    suspend fun loadMovies(): MoviesListResponse
}