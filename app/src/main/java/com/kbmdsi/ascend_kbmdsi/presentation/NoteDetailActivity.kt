package com.kbmdsi.ascend_kbmdsi.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.DBHelper
import com.kbmdsi.ascend_kbmdsi.data.NoteRepository

class NoteDetailActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notedetail)

        val title = findViewById<EditText>(R.id.etTitle)
        val content = findViewById<EditText>(R.id.etDescription)
        val priority = findViewById<Spinner>(R.id.spPriority)
        val back = findViewById<ImageView>(R.id.imageView3)
        val delete = findViewById<CardView>(R.id.deletenote)

        val dbHelper = DBHelper(this)
        val noteRepository = NoteRepository(dbHelper)
        val viewModel = NoteViewModel(noteRepository)

        title.text = SpannableStringBuilder(intent.getStringExtra("note_title"))
        content.text = SpannableStringBuilder(intent.getStringExtra("note_content"))
        priority.setSelection(intent.getIntExtra("note_priority", 1) - 1)

        back.setOnClickListener {
            finish()
        }

        delete.setOnClickListener {
            intent.getStringExtra("note_id")?.let { id ->
                viewModel.deleteNote(
                    id
                )
            }
            finish()
        }
    }
}