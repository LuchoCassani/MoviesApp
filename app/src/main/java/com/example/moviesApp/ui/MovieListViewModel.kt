package com.example.moviesApp.ui

import androidx.lifecycle.ViewModel
import com.example.moviesApp.data.Movie
import com.example.moviesApp.repository.MoviesRepository

class MovieListViewModel: ViewModel() {
    val movies = MoviesRepository.getNowPlayingMovies()
}