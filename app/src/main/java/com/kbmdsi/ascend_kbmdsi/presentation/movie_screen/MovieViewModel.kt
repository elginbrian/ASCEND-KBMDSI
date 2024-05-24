package com.kbmdsi.ascend_kbmdsi.presentation.movie_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieAPI
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieResponse
import kotlinx.coroutines.launch

class MovieViewModel(
    private val api: MovieAPI
): ViewModel() {

    init {
        Log.d("MovieViewModel", "init called")
        getMovies {
            Log.d("MovieViewModel", "getMovies: $it")
        }
    }
    fun getMovies(
        onFinished: (MovieResponse) -> Unit
    ){
        Log.d("MovieViewModel", "getMovies called")
        viewModelScope.launch {
            api.fetchMovies(
                successListener = {
                    onFinished(it)
                },
                errorListener = {

                }
            )
        }
    }
}