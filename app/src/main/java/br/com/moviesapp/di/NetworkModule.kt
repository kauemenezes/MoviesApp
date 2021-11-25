package br.com.moviesapp.di

import android.content.Context
import android.content.SharedPreferences
import br.com.moviesapp.BuildConfig
import br.com.moviesapp.data.api.MoviesApiService
import br.com.moviesapp.data.repository.MoviesRepositoryImpl
import br.com.moviesapp.data.source.MovieDataSource
import br.com.moviesapp.data.utils.SharedPrefsHelper
import br.com.moviesapp.domain.repository.MoviesRepository
import br.com.moviesapp.util.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideRetrofitClient() }
    single {
        get<Retrofit>().create(MoviesApiService::class.java)
    }
    single { MovieDataSource(get(), get()) }
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), provideIoDispatcher())
    }
    single { provideSharedPreferences(androidContext()) }
    single { SharedPrefsHelper(get()) }
}

fun provideRetrofitClient() =
    retrofitClient(okHttpClient())

private fun okHttpClient() =
    OkHttpClient.Builder().run {
        addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            readTimeout(60L, TimeUnit.SECONDS)
            connectTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
        })
        build()
    }

private fun retrofitClient(httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().run {
        baseUrl(Constants.BASE_URL)
        client(httpClient)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }

fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(
        SharedPrefsHelper.PREF_LIST_ORDER,
        Context.MODE_PRIVATE
    )
}