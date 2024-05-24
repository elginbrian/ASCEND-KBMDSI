package com.kbmdsi.ascend_kbmdsi.data.remote

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val api: MovieAPI
){
    suspend fun getMovies(): Flow<MovieResponse> {
        return flow {
            try {
                var response = MovieResponse(
                    page = 0,
                    results = emptyList(),
                    totalPages = 0,
                    totalResults = 0
                )
                api.fetchMovies(
                    successListener = {
                        response = it
                        Log.d("MovieRepository", "getMovies: ${it.results}")
                    },
                    errorListener = {
                        Log.d("MovieRepository", "getMovies: ${it}")
                    }
                )
                emit(response)
                return@flow
            } catch (e: Exception){
                Log.d("MovieRepository", "getMovies: ${e.message}")
                emit(MovieResponse(
                    page = 0,
                    results = emptyList(),
                    totalPages = 0,
                    totalResults = 0
                ))
                return@flow
            }
        }
    }
}