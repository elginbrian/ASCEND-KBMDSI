package com.kbmdsi.ascend_kbmdsi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieAPI
import com.kbmdsi.ascend_kbmdsi.presentation.example.IntentActivity
import com.kbmdsi.ascend_kbmdsi.presentation.example.LivecycleActivity
import com.kbmdsi.ascend_kbmdsi.presentation.note_screen.HomeActivity
import com.kbmdsi.ascend_kbmdsi.presentation.movie_screen.MovieViewModel

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

//        Intent(this, HomeActivity::class.java).also {
//            startActivity(it)
//        }

//        Intent(this, LivecycleActivity::class.java).also {
//            startActivity(it)
//        }

        Intent(this, IntentActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }
}