package com.kbmdsi.ascend_kbmdsi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)

        var back = findViewById<ImageView>(R.id.imageView3)

        back.setOnClickListener {
            finish()
        }
    }
}