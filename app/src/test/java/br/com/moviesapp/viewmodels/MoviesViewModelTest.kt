package br.com.moviesapp.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.moviesapp.BaseViewModelTest
import br.com.moviesapp.commons.Success
import br.com.moviesapp.domain.models.Movie
import br.com.moviesapp.domain.usecases.GetMoviesUseCase
import br.com.moviesapp.domain.usecases.LoadMoviesUseCase
import br.com.moviesapp.ui.movies.MoviesViewModel
import br.com.moviesapp.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MoviesViewModelTest : BaseViewModelTest() {

    private var loadMoviesUseCase = mockk<LoadMoviesUseCase>()
    private var getMoviesUseCase = mockk<GetMoviesUseCase>()
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var movieEntityOne: Movie
    private lateinit var movieEntityTwo: Movie

    @Before
    fun setup() {
        moviesViewModel = MoviesViewModel(loadMoviesUseCase, getMoviesUseCase)
        movieEntityOne = Movie(
            id = "1",
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
        )

        movieEntityTwo = Movie(
            id = "2",
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
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeLoadMovies_setsUiStatusEvent() {
        runBlockingTest {
            coEvery { loadMoviesUseCase() } returns Unit
            moviesViewModel.loadMovies(true)
            val uiStatus = moviesViewModel.uiState.getOrAwaitValue()
            MatcherAssert.assertThat(uiStatus, CoreMatchers.instanceOf(Success::class.java))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovies_confirmObtained() {
        runBlockingTest {
            coEvery { getMoviesUseCase() } returns flow {
                emit(listOf(movieEntityOne, movieEntityTwo))
            }
            val movies = moviesViewModel.movies.getOrAwaitValue()

            // Verify the obtained statements
            MatcherAssert.assertThat(movies, CoreMatchers.not(emptyList()))
            MatcherAssert.assertThat(movies, IsCollectionWithSize.hasSize(2))
        }
    }
}