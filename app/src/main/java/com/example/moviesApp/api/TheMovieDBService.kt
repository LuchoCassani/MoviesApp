package com.example.moviesApp.api



import com.example.moviesApp.data.Genres
import com.example.moviesApp.data.NowPlayingMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key")apiKey: String,
        @Query("language")language:String,
        @Query("page")page:Int,
        @Query("region")region:String

    ): Call<NowPlayingMovies>

    @GET("genre/movie/list")
    fun getGenreList(
        @Query("api_key")apiKey: String,
        @Query("language")language:String,

    ): Call<Genres>
}