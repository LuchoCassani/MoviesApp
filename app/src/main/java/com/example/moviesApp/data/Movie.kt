package com.example.moviesApp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

@Entity(primaryKeys = ["title", "overview"])
data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val vote: Double,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("overview") val overview: String):

    Serializable {
    var urlImage:String = ""
    var genres: List<String> = mutableListOf()

    fun getGenresString(): String {
        val stringBuilder = StringBuilder()
        for (genre in genres) {
            stringBuilder.append(genre)
            stringBuilder.append(", ")
        }
        return stringBuilder.toString().removeSuffix(", ")
    }
}