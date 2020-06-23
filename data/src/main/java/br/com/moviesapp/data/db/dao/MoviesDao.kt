package br.com.moviesapp.data.db.dao

import androidx.room.*
import br.com.moviesapp.data.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class MoviesDao {

    @Query("SELECT * FROM movies")
    abstract fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    abstract fun getMovie(movieId: String): Flow<MovieEntity>

    fun getMovieDistinctUntilChanged(movieId: String) =
        getMovie(movieId).distinctUntilChanged()

    @Transaction
    open suspend fun clearAndInsert(movies: List<MovieEntity>) {
        delete()
        insert(movies)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    abstract suspend fun delete()
}