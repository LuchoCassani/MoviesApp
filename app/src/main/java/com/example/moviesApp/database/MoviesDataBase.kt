package com.example.moviesApp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesApp.data.Converters
import com.example.moviesApp.data.Genre
import com.example.moviesApp.data.Movie

@Database(entities = [Movie::class, Genre::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): DaoGenre
}