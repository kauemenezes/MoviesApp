package br.com.moviesapp.data.mappers

import br.com.moviesapp.data.db.entity.MovieEntity
import br.com.moviesapp.domain.models.Movie


internal fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
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

internal fun List<MovieEntity>.toDomain(): List<Movie> {
    return this.map {
        it.toDomain()
    }
}