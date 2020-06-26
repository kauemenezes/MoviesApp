package br.com.moviesapp.db.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.moviesapp.data.db.MoviesDatabase
import br.com.moviesapp.data.db.dao.MoviesDao
import br.com.moviesapp.data.db.entity.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {

    private lateinit var moviesDao: MoviesDao
    private lateinit var moviesDatabase: MoviesDatabase
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
    fun insertMovies_confirmInserted() = runBlocking {
        // Add new movies
        moviesDao.clearAndInsert(listOf(movieEntityOne, movieEntityTwo))

        // Verify
        moviesDao.getMovies().take(1).collect {
            assertThat(it, not(emptyList()))
            assertThat(it, IsCollectionWithSize.hasSize(2))
        }
    }

    @Test
    fun insertMovies_confirmDeleted() = runBlocking{
        // Add new statements
        moviesDao.clearAndInsert(listOf(movieEntityOne, movieEntityTwo))

        // Delete the previously inserted movies
        moviesDao.delete()

        // Verify
        moviesDao.getMovies().take(1).collect {
            assertThat(it, `is`(emptyList()))
        }
    }
}