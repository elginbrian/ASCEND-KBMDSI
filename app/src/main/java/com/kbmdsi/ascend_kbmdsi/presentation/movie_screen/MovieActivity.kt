package com.kbmdsi.ascend_kbmdsi.presentation.movie_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.remote.Movie
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieAPI
import com.kbmdsi.ascend_kbmdsi.presentation.note_screen.HomeActivity
import com.kbmdsi.ascend_kbmdsi.presentation.note_screen.TopAndBottomSpacerItemDecoration

class MovieActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val api        = MovieAPI(this)
        val viewModel  = MovieViewModel(api)
        var movies     = mutableListOf<Movie>()

        viewModel.getMovies {
            movies = it.results.toMutableList()
            Log.d("MovieActivity", movies.toString())

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            val adapter      = MovieAdapter(movies, this)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter.notifyDataSetChanged()
            recyclerView.addItemDecoration(TopAndBottomSpacerItemDecoration(32, 72))
        }

        val notes = findViewById<ImageView>(R.id.imageView2)
        notes.setOnClickListener {
            finish()
        }
    }
}