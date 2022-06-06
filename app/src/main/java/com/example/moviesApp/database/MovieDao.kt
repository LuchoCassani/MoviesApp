package com.example.moviesApp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesApp.data.Movie

@Dao
interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Update
    fun refreshMovie(movie:Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movie")
    fun allMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE vote > :minimumVote")
    fun moviesWithMinimumVote(minimumVote: Double): List<Movie>
}