package com.kbmdsi.ascend_kbmdsi.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.DBHelper
import com.kbmdsi.ascend_kbmdsi.data.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.NoteRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class HomeActivity(): AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val dbHelper = DBHelper(this)
        val noteRepository = NoteRepository(dbHelper)
        val viewModel = NoteViewModel(noteRepository)
//        viewModel.addNote(NoteModel(
//            id = UUID.randomUUID().toString(),
//            title = "Title",
//            content = "Description",
//            date = LocalDateTime.now().toString()
//        ))

        viewModel.getNotes()

        var addnote = findViewById<CardView>(R.id.addnote)
        addnote.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}