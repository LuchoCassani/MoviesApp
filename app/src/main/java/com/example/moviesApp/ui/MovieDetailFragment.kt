package com.example.moviesApp.ui

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.movie_detail, container, false)

        movie?.let {
            rootView.findViewById<TextView>(R.id.item_detail).text = it.overview
            rootView.findViewById<TextView>(R.id.genre_textview).text = it.getGenerosString()
            val poster = rootView.findViewById<ImageView>(R.id.poster_image)
            Glide
                .with(this)
                .load(it.urlImage).into(poster)
        }

        return rootView
    }

    companion object {

        const val ARG_ITEM = "item"
    }
}