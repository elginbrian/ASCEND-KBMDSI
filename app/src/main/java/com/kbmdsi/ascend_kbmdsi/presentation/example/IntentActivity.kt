package com.kbmdsi.ascend_kbmdsi.presentation.example

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.presentation.note_screen.HomeActivity

class IntentActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        findViewById<Button>(R.id.implicitIntent).setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.explicitIntent).setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.extras).setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                it.putExtra("message", "This are Extras")
                startActivity(it)
            }
        }
    }
}