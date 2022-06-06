package com.example.moviesApp.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesApp.api.RetrofitServiceBuilder
import com.example.moviesApp.api.TheMovieDBService
import com.example.moviesApp.data.Genres
import com.example.moviesApp.data.Movie
import com.example.moviesApp.data.NowPlayingMovies
import retrofit2.Call
import retrofit2.Response


private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "469460d6d02547600acf9e2551bd0fc4"
private const val IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w500/"

object MoviesRepository {
    private val moviesService: TheMovieDBService =
        RetrofitServiceBuilder(BASE_URL).buildService(TheMovieDBService::class.java)
    private val completeMoviesLiveData = MutableLiveData<List<Movie>> ()

    fun getNowPlayingMovies():LiveData<List<Movie>>{
        val call = moviesService.getNowPlayingMovies(API_KEY,"es",1,"AR")
        call.enqueue(object: retrofit2.Callback<NowPlayingMovies>{
            override fun onResponse(
                call: Call<NowPlayingMovies>,
                response: Response<NowPlayingMovies>
            ) {
                if(response.isSuccessful){
                    val movies = response.body()?.movies ?: listOf()
                    completeInfo(movies)
                }
            }

            override fun onFailure(call: Call<NowPlayingMovies>, t: Throwable) {

            }

        })
        return completeMoviesLiveData
    }

    private fun completeInfo(movies: List<Movie>) {
        val call = moviesService.getGenreList(API_KEY,"en")
        call.enqueue(object: retrofit2.Callback<Genres>{
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if (response.isSuccessful){
                    val genres = response.body()?.genres
                    val genresMap = genres?.associateBy ({ it.id },{it.name})
                    genresMap?.let { map ->
                        movies.forEach { movie ->
                            movie.genres = movie.genreIds.map {map[it] ?: ""}
                            movie.urlImage = IMAGE_URL_BASE + movie.posterPath

                        }
                        completeMoviesLiveData.value = movies

                    }
                }
            }

            override fun onFailure(call: Call<Genres>, t: Throwable) {

            }

        }

        )
    }

}