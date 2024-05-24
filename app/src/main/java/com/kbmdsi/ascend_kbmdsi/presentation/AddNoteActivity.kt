package com.kbmdsi.ascend_kbmdsi.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.data.DBHelper
import com.kbmdsi.ascend_kbmdsi.data.NoteModel
import com.kbmdsi.ascend_kbmdsi.data.NoteRepository
import java.time.LocalDateTime
import java.util.UUID

class AddNoteActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        val back    = findViewById<ImageView>(R.id.imageView3)
        val spinner = findViewById<Spinner>(R.id.spPriority)
        val check   = findViewById<ImageView>(R.id.imageView2)
        var selectedPriority = spinner.selectedItem.toString()

        val dbHelper = DBHelper(this)
        val noteRepository = NoteRepository(dbHelper)
        val viewModel = NoteViewModel(noteRepository)

        back.setOnClickListener {
            finish()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedPriority = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        check.setOnClickListener {
            viewModel.addNote(NoteModel(
                id = UUID.randomUUID().toString(),
                title = findViewById<EditText>(R.id.etTitle).text.toString(),
                content = findViewById<EditText>(R.id.etDescription).text.toString(),
                priority = when(selectedPriority){
                    "High Priority" -> 1
                    "Medium Priority" -> 2
                    "Low Priority" -> 3
                    else -> 0 },
                date = LocalDateTime.now().toString()
            ))
            finish()
            viewModel.getNotes {

            }
        }
    }
}