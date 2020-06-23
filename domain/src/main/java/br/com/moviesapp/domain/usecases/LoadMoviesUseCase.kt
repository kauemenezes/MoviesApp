package br.com.moviesapp.domain.usecases

import br.com.moviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(userId: Int) =
        moviesRepository.loadMovies()
}