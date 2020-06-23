package br.com.moviesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.moviesapp.data.db.dao.MoviesDao
import br.com.moviesapp.data.db.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object{
        val DATABASE_NAME: String = "movies_db"
    }
}