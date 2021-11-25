package br.com.moviesapp.domain.usecases

import br.com.moviesapp.domain.repository.MoviesRepository

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke() =
        moviesRepository.getMovies()
}