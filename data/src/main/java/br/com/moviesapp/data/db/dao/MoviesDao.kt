package br.com.moviesapp.data.db.dao

import androidx.room.*
import br.com.moviesapp.data.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: String): Flow<MovieEntity>

    fun getMovieDistinctUntilChanged(movieId: String) =
        getMovie(movieId).distinctUntilChanged()

    @Transaction
    suspend fun clearAndInsert(movies: List<MovieEntity>) {
        delete()
        insert(movies)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun delete()
}