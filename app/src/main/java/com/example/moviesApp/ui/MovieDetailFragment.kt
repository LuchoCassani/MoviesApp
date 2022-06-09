package com.example.moviesApp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moviesApp.R
import com.example.moviesApp.data.Movie
import com.example.moviesApp.databinding.MovieDetailBinding


class MovieDetailFragment : Fragment() {



    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                movie = it.getSerializable(ARG_ITEM) as Movie?
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = movie?.title
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = MovieDetailBinding.inflate(inflater, container,false)

        movie?.let {
            binding.itemDetail.text = it.overview
            binding.genreTextview.text = it.getGenresString()
            binding.releaseDate.text = it.releaseDate
            val poster = binding.posterImage
            Glide
                .with(this)
                .load(it.urlImage).into(poster)
        }

        return binding.root
    }

    companion object {

        const val ARG_ITEM = "item"
    }
}