package br.com.moviesapp.data.api.network_responses

data class MoviesListResponse(
    var statementList: List<MovieResponse>? = null
)