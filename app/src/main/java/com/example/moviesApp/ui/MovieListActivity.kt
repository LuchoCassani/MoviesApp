package com.example.moviesApp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.moviesApp.R
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.moviesApp.data.Movie

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        val moviesObserver = Observer<List<Movie>>{movies ->
            setupRecyclerView(findViewById(R.id.item_list), movies)
        }
        viewModel.movies.observe(this, moviesObserver)


    }

    private fun setupRecyclerView(recyclerView: RecyclerView, movies: List<Movie>) {
        recyclerView.adapter = SimpleFilmRecyclerViewAdapter(this, movies)
    }

    class SimpleFilmRecyclerViewAdapter(private val parentActivity: MovieListActivity,
                                        private val values: List<Movie>) :
            RecyclerView.Adapter<SimpleFilmRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Movie

                val intent = Intent(v.context, MovieDetailActivity::class.java).apply {
                    putExtra(MovieDetailFragment.ARG_ITEM, item)
                }
                v.context.startActivity(intent)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val movie = values[position]
            holder.idView.text = movie.vote.toString()
            holder.contentView.text = movie.title

            with(holder.itemView) {
                tag = movie
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.findViewById(R.id.id_text)
            val contentView: TextView = view.findViewById(R.id.content)
        }
    }
}