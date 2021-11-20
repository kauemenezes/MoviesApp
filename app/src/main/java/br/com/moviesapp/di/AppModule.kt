package br.com.moviesapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import br.com.moviesapp.BuildConfig
import br.com.moviesapp.data.api.MoviesApiService
import br.com.moviesapp.data.db.MoviesDatabase
import br.com.moviesapp.data.utils.SharedPrefsHelper
import br.com.moviesapp.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).also {
                it.connectTimeout(20, TimeUnit.SECONDS)
                it.readTimeout(15, TimeUnit.SECONDS)
                it.writeTimeout(15, TimeUnit.SECONDS)
            }.build()

    @Provides
    fun provideLoggingInterceptor() =
            HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder:  Gson, okhttpClient: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDb(context: Context): MoviesDatabase {
        return Room
            .databaseBuilder(context, MoviesDatabase::class.java, MoviesDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPrefsHelper.PREF_LIST_ORDER,
            Context.MODE_PRIVATE
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideMoviesApiService(retrofitBuilder: Retrofit.Builder): MoviesApiService {
        return retrofitBuilder
            .build()
            .create(MoviesApiService::class.java)
    }

    @JvmStatic
    @Singleton
    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}