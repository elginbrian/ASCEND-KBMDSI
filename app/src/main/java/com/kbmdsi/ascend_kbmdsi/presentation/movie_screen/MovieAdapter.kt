package com.kbmdsi.ascend_kbmdsi.presentation.movie_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.local.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.remote.Movie
import com.kbmdsi.ascend_kbmdsi.presentation.note_screen.NoteAdapter

class MovieAdapter(
    private val movies: List<Movie>,
    private val context: Context
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originalTitle = itemView.findViewById<TextView>(R.id.textView5);
        val overview = itemView.findViewById<TextView>(R.id.textView6);
        val releaseDate = itemView.findViewById<TextView>(R.id.textView4);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.originalTitle.text = movie.title
        holder.overview.text = movie.overview
        holder.releaseDate.text = "Popularity: " + movie.popularity.toString()
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}