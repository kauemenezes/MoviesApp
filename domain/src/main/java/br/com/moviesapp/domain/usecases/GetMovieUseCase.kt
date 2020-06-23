package br.com.moviesapp.domain.usecases

import br.com.moviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(movieId: String) =
        moviesRepository.getMovie(movieId)
}