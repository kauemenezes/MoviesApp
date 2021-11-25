package br.com.moviesapp.domain.usecases

import br.com.moviesapp.domain.repository.MoviesRepository

class GetMovieUseCase(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(movieId: String) =
        moviesRepository.getMovie(movieId)
}