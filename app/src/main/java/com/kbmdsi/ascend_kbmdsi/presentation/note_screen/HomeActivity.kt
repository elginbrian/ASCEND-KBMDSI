package com.kbmdsi.ascend_kbmdsi.presentation.note_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.local.DBHelper
import com.kbmdsi.ascend_kbmdsi.data.local.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.local.NoteRepository
import com.kbmdsi.ascend_kbmdsi.presentation.movie_screen.MovieActivity


class HomeActivity(): AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kbmdsi.ascend_kbmdsi.R.layout.activity_home)

        if(!intent.getStringExtra("message").isNullOrEmpty()){
            Toast.makeText(this, intent.getStringExtra("message"), Toast.LENGTH_SHORT).show()
        }

        val dbHelper = DBHelper(this)
        val noteRepository = NoteRepository(dbHelper)
        val viewModel = NoteViewModel(noteRepository)

        var notes: MutableList<NoteModel>
        viewModel.getNotes {
            notes = it.sortedByDescending { note -> note.date }.toMutableList()
            Log.d("HomeActivity", "onCreate: $notes")

            val recyclerView = findViewById<RecyclerView>(com.kbmdsi.ascend_kbmdsi.R.id.recyclerView)
            val adapter = NoteAdapter(notes, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(TopAndBottomSpacerItemDecoration(32, 72))
            adapter.notifyDataSetChanged()
        }
        
        var addnote = findViewById<CardView>(com.kbmdsi.ascend_kbmdsi.R.id.addnote)
        addnote.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        val movie = findViewById<ImageView>(R.id.imageView2)
        movie.setOnClickListener {
            Intent(this, MovieActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}

class TopAndBottomSpacerItemDecoration(private val top: Int, private val bottom: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = top
        } else if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.bottom = bottom
        }
    }
}