package com.example.moviesApp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.example.moviesApp.R
import com.example.moviesApp.databinding.ActivityMovieDetailBinding


class MovieDetailActivity : AppCompatActivity() {

  private lateinit var binding:ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate((layoutInflater))
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        if (savedInstanceState == null) {

            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MovieDetailFragment.ARG_ITEM,
                        intent.getSerializableExtra(MovieDetailFragment.ARG_ITEM))
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, MovieListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}