package br.com.moviesapp.source

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.moviesapp.data.api.MoviesApiService
import br.com.moviesapp.data.db.MoviesDatabase
import br.com.moviesapp.data.db.dao.MoviesDao
import br.com.moviesapp.data.db.entity.MovieEntity
import br.com.moviesapp.data.source.MovieDataSource
import br.com.moviesapp.utils.SampleData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@RunWith(AndroidJUnit4::class)
class MovieDataSourceTest {

    private lateinit var moviesDao: MoviesDao
    private lateinit var moviesDataSource: MovieDataSource
    private lateinit var moviesDatabase: MoviesDatabase
    private var mockkMoviesApiService = mockk<MoviesApiService>()
    private lateinit var movieEntityOne: MovieEntity
    private lateinit var movieEntityTwo: MovieEntity

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        moviesDatabase = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .allowMainThreadQueries() // Allowing main thread queries, just for testing.
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        moviesDao = moviesDatabase.moviesDao()
        moviesDataSource = MovieDataSource(mockkMoviesApiService, moviesDao)

        movieEntityOne = MovieEntity(
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

        movieEntityTwo = MovieEntity(
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

    @After
    fun closeDb() {
        moviesDatabase.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeLoadMovies_verifyMovies() = runBlocking {
        coEvery { mockkMoviesApiService.loadMovies() } returns SampleData.moviesResponse
        moviesDataSource.loadMovies()

        moviesDao.getMovies().take(1).collect {
            MatcherAssert.assertThat(it, CoreMatchers.not(emptyList()))
            MatcherAssert.assertThat(it, IsCollectionWithSize.hasSize(2))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovies_confirmObtained() = runBlocking {
        moviesDao.clearAndInsert(listOf(movieEntityOne, movieEntityTwo))

        moviesDataSource.getMovies().take(1).collect {
            MatcherAssert.assertThat(it, CoreMatchers.not(emptyList()))
            MatcherAssert.assertThat(it, IsCollectionWithSize.hasSize(2))
        }
    }
}