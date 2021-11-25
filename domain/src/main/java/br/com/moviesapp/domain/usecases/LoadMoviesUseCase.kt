package br.com.moviesapp.domain.usecases

import br.com.moviesapp.domain.repository.MoviesRepository

class LoadMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke() =
        moviesRepository.loadMovies()
}