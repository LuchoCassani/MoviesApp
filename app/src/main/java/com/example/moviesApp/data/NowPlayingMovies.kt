package com.example.moviesApp.data

import com.google.gson.annotations.SerializedName

data class NowPlayingMovies (
    @SerializedName("results") val movies: List<Movie>
)