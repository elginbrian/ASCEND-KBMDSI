package com.kbmdsi.ascend_kbmdsi

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class AscendActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ascend)

        var count       = 0
        var editText    = findViewById<EditText>(R.id.editText)
        var increment   = findViewById<Button>(R.id.button)
        var display     = findViewById<TextView>(R.id.textView)
        var pickImage   = findViewById<CardView>(R.id.pickImage)
        var image       = findViewById<ImageView>(R.id.imageView)
        var back        = findViewById<CardView>(R.id.back)
        var purpleCard  = findViewById<CardView>(R.id.materialCardView)
        var greenCard   = findViewById<CardView>(R.id.materialCardView6)
        var redCard     = findViewById<CardView>(R.id.materialCardView5)

        var src         = listOf(
            R.drawable.ascendimage,
            R.drawable.ascendimage2,
            R.drawable.ascendimage3,
            R.drawable.ascendimage4,
            R.drawable.ascendimage5
        )

        editText.setOnKeyListener { _, keyCode, event ->
            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                Toast.makeText(this, editText.text, Toast.LENGTH_SHORT).show()
                true
            } else {
                false
            }
        }

        increment.setOnClickListener {
            count++
            display.text = count.toString()
        }

        pickImage.setOnClickListener {
            image.setImageResource(src.random())
        }

        back.setOnClickListener {
            finish()
        }

        purpleCard.setOnClickListener {
            increment.setBackgroundColor(Color.parseColor("#61008D"))
            pickImage.setCardBackgroundColor(Color.parseColor("#61008D"))
            back.setCardBackgroundColor(Color.parseColor("#61008D"))
        }

        greenCard.setOnClickListener{
            increment.setBackgroundColor(Color.parseColor("#4CAF50"))
            pickImage.setCardBackgroundColor(Color.parseColor("#4CAF50"))
            back.setCardBackgroundColor(Color.parseColor("#4CAF50"))
        }

        redCard.setOnClickListener {
            increment.setBackgroundColor(Color.parseColor("#E91E63"))
            pickImage.setCardBackgroundColor(Color.parseColor("#E91E63"))
            back.setCardBackgroundColor(Color.parseColor("#E91E63"))
        }
    }
}