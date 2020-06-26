package br.com.moviesapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.moviesapp.BaseServiceTest
import br.com.moviesapp.data.api.MoviesApiService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MoviesApiServiceTest : BaseServiceTest() {

    private lateinit var service: MoviesApiService

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun setup() {
        super.setup()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }

    @Test
    fun requestMovies_confirmResponse() {
        runBlocking {
            val resultResponse = service.loadMovies()
            val request = mockWebServer.takeRequest()

            MatcherAssert.assertThat(resultResponse, CoreMatchers.not(CoreMatchers.nullValue()))
            MatcherAssert.assertThat(resultResponse, CoreMatchers.not(CoreMatchers.nullValue()))
            MatcherAssert.assertThat(resultResponse, IsCollectionWithSize.hasSize(4))
            MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/saga"))
        }
    }
}