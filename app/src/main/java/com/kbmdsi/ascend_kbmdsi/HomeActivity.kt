package com.kbmdsi.ascend_kbmdsi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HomeActivity(): AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var addnote = findViewById<CardView>(R.id.addnote)

        addnote.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}