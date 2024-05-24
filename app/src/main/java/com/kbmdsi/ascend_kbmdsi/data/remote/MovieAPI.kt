package com.kbmdsi.ascend_kbmdsi.data.remote

import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.util.concurrent.ExecutionException

class MovieAPI(context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private val gson = Gson()

    fun fetchMovies(
        successListener: (MovieResponse) -> Unit,
        errorListener: (String) -> Unit
    ) {
        val url = "https://api.themoviedb.org/3/discover/movie"

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val movieResponse: MovieResponse = gson.fromJson(response.toString(), MovieResponse::class.java)
                    successListener(movieResponse)
                } catch (e: Exception) {
                    errorListener(e.message ?: "An error occurred")
                }
            },
            Response.ErrorListener { error ->
                errorListener(error.toString())
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["accept"] = "application/json"
                headers["Authorization"] = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MjRiY2JmMzg5NDk4Y2JhZjZkMTlhNGU1ODdjMDIwNiIsInN1YiI6IjY2MWRkODdiMDgxNmM3MDE2M2VkODczNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2uDQJHrJuSKHWG_LIdefthyODXS2qFjEsZP3zBno3IA"
                return headers
            }
        }

        requestQueue.add(jsonObjectRequest)
    }
}