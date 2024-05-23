package com.kbmdsi.ascend_kbmdsi.example

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.kbmdsi.ascend_kbmdsi.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class AscendActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ascend)

        var count       = 0
        var editText    = findViewById<EditText>(R.id.editText)
        var increment   = findViewById<Button>(R.id.button)
        var countdown   = findViewById<Button>(R.id.button2)
        var display     = findViewById<TextView>(R.id.textView)
        var pickImage   = findViewById<CardView>(R.id.pickImage)
        var image       = findViewById<ImageView>(R.id.imageView)
        var back        = findViewById<CardView>(R.id.back)
        var purpleCard  = findViewById<CardView>(R.id.materialCardView)
        var greenCard   = findViewById<CardView>(R.id.materialCardView6)
        var redCard     = findViewById<CardView>(R.id.materialCardView5)
        var hideButton  = findViewById<CardView>(R.id.hideImage)

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
            countdown.setBackgroundColor(Color.parseColor("#61008D"))
        }

        greenCard.setOnClickListener{
            increment.setBackgroundColor(Color.parseColor("#4CAF50"))
            pickImage.setCardBackgroundColor(Color.parseColor("#4CAF50"))
            back.setCardBackgroundColor(Color.parseColor("#4CAF50"))
            countdown.setBackgroundColor(Color.parseColor("#4CAF50"))
        }

        redCard.setOnClickListener {
            increment.setBackgroundColor(Color.parseColor("#E91E63"))
            pickImage.setCardBackgroundColor(Color.parseColor("#E91E63"))
            back.setCardBackgroundColor(Color.parseColor("#E91E63"))
            countdown.setBackgroundColor(Color.parseColor("#E91E63"))
        }

        countdown.setOnClickListener {
            lifecycleScope.launch {
                countdown(count).collect{
                    display.text = it.toString()
                }
                if(display.text == "0"){
                    count = 0
                    Toast.makeText(applicationContext, "Countdown Complete", Toast.LENGTH_SHORT).show()
                }
            }
        }

        hideButton.setOnClickListener {
            if(image.visibility == ImageView.INVISIBLE){
                image.visibility = ImageView.VISIBLE
            } else {
                image.visibility = ImageView.INVISIBLE
            }
        }
    }

    private fun countdown(start: Int): Flow<Int> {
        return flow {
            for (i in start downTo 0) {
                emit(i)
                delay(1000)
            }
            return@flow
        }
    }
}