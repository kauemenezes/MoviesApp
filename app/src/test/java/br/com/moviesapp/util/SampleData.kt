package br.com.moviesapp.util

import br.com.moviesapp.data.api.network_responses.MovieResponse


object SampleData {
    val moviesResponse = listOf(
        MovieResponse(
            title = "Iron Man",
            year = "2008",
            rated = "PG-13",
            released = "02 May 2008",
            runtime = "",
            genre = "",
            director = "",
            writer = "",
            actors = "",
            plot = "",
            poster = ""
        ),
        MovieResponse(
            title = "The Incredible Hulk",
            year = "2008",
            rated = "PG-13",
            released = "13 Jun 2008",
            runtime = "",
            genre = "",
            director = "",
            writer = "",
            actors = "",
            plot = "",
            poster = ""
        )
    )
}