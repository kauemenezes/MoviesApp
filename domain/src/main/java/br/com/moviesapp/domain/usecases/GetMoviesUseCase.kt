package br.com.moviesapp.domain.usecases

import br.com.moviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke() =
        moviesRepository.getMovies()
}