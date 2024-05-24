package com.kbmdsi.ascend_kbmdsi.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.DBHelper
import com.kbmdsi.ascend_kbmdsi.data.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.NoteRepository
import java.time.LocalDateTime
import java.util.UUID


class HomeActivity(): AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kbmdsi.ascend_kbmdsi.R.layout.activity_home)

        val dbHelper = DBHelper(this)
        val noteRepository = NoteRepository(dbHelper)
        val viewModel = NoteViewModel(noteRepository)

        var notes: MutableList<NoteModel>
        viewModel.getNotes {
            notes = it.toMutableList()
            Log.d("HomeActivity", "onCreate: $notes")

            val recyclerView = findViewById<RecyclerView>(com.kbmdsi.ascend_kbmdsi.R.id.recyclerView)
            val adapter = Adapter(notes, this)
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