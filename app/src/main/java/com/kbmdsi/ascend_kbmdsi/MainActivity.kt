package com.kbmdsi.ascend_kbmdsi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieAPI
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieRepository
import com.kbmdsi.ascend_kbmdsi.presentation.HomeActivity
import com.kbmdsi.ascend_kbmdsi.presentation.MovieViewModel

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val MovieAPI = MovieAPI(this)
        val movieRepository = MovieRepository(MovieAPI)
        val viewModel = MovieViewModel(movieRepository)

        viewModel.getMovies {

        }
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
    }
}