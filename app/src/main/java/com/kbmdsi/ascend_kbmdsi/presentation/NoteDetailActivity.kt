package com.kbmdsi.ascend_kbmdsi.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.local.DBHelper
import com.kbmdsi.ascend_kbmdsi.data.local.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.local.NoteRepository
import java.time.LocalDateTime

class NoteDetailActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notedetail)

        val title    = findViewById<EditText>(R.id.etTitle)
        val content  = findViewById<EditText>(R.id.etDescription)
        val priority = findViewById<Spinner>(R.id.spPriority)
        val back     = findViewById<ImageView>(R.id.imageView3)
        val delete   = findViewById<CardView>(R.id.deletenote)
        val check    = findViewById<ImageView>(R.id.imageView2)

        val dbHelper = DBHelper(this)
        val noteRepository = NoteRepository(dbHelper)
        val viewModel = NoteViewModel(noteRepository)

        title.text = SpannableStringBuilder(intent.getStringExtra("note_title"))
        content.text = SpannableStringBuilder(intent.getStringExtra("note_content"))
        priority.setSelection(intent.getIntExtra("note_priority", 1) - 1)

        back.setOnClickListener {
            Intent(this, HomeActivity::class.java).also{
                startActivity(it)
            }
            finish()
        }

        delete.setOnClickListener {
            intent.getStringExtra("note_id")?.let { id ->
                viewModel.deleteNote(
                    id
                ){
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

                    Intent(this, HomeActivity::class.java).also{ intent ->
                        startActivity(intent)
                    }
                    finish()
                }
            }
        }

        check.setOnClickListener {
            viewModel.updateNote(
                NoteModel(
                id = intent.getStringExtra("note_id")!!,
                title = title.text.toString(),
                content = content.text.toString(),
                priority = priority.selectedItemPosition + 1,
                date = LocalDateTime.now().toString()
            )
            ){
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

                Intent(this, HomeActivity::class.java).also{ intent ->
                    startActivity(intent)
                }
                finish()
            }
        }
    }
}