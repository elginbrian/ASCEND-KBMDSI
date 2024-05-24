package com.kbmdsi.ascend_kbmdsi.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieRepository
import com.kbmdsi.ascend_kbmdsi.data.remote.MovieResponse
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {

    init {
        getMovies {
            Log.d("MovieViewModel", "getMovies: $it")
        }
    }
    fun getMovies(
        onFinished: (MovieResponse) -> Unit
    ){
        viewModelScope.launch {
            movieRepository.getMovies().collect{
                onFinished(it)
                Log.d("MovieViewModel", "getMovies: $it")
            }
        }
    }
}