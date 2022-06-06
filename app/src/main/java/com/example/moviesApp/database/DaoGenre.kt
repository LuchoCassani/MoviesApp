package com.example.moviesApp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesApp.data.Genre
@Dao
interface DaoGenre {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(genres: List<Genre>)

    @Query("SELECT * FROM genre")
    fun load(): LiveData<Genre>
}