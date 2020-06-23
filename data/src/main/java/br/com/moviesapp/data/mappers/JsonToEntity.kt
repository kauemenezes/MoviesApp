package br.com.moviesapp.data.mappers

import br.com.moviesapp.data.api.network_responses.MovieResponse
import br.com.moviesapp.data.db.entity.MovieEntity


internal fun MovieResponse.toEntity(): MovieEntity {
    return MovieEntity(
        id = "${this.title?.replace(" ", "")}:${this.year}",
        title = this.title,
        year = this.year,
        rated = this.rated,
        released = this.released,
        runtime = this.runtime,
        genre = this.genre,
        director = this.director,
        writer = this.writer,
        actors = this.actors,
        plot = this.plot,
        poster = this.poster
    )
}

internal fun List<MovieResponse>.toEntity(): List<MovieEntity> {
    return this.map {
        it.toEntity()
    }
}