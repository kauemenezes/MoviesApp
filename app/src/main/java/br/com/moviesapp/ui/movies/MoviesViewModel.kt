package br.com.moviesapp.ui.movies

import androidx.lifecycle.*
import br.com.moviesapp.commons.Loading
import br.com.moviesapp.commons.Success
import br.com.moviesapp.domain.models.Movie
import br.com.moviesapp.domain.usecases.GetMoviesUseCase
import br.com.moviesapp.domain.usecases.LoadMoviesUseCase
import br.com.moviesapp.ui.UiStateViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : UiStateViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    val movies: LiveData<List<Movie>> = liveData {
        getMoviesUseCase().collect {
            emit(it)
        }
    }

    fun loadMovies(forceRefresh: Boolean) {
        if (!forceRefresh) _uiState.value = Loading
        viewModelScope.launch(handler) {
            loadMoviesUseCase()
            _uiState.value = Success
        }
    }

    fun setMovie(movie: Movie) {
        _movie.value = movie
    }
}