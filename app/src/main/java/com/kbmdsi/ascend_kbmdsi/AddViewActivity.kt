package com.kbmdsi.ascend_kbmdsi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddViewActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addview)

        var count        = 0
        var linearLayout = findViewById<LinearLayout>(R.id.linearLayout1)
        var button       = findViewById<Button>(R.id.addView)

        button.setOnClickListener {
            var textView = TextView(this)
            textView.setText(count.toString())
            linearLayout.addView(textView)
            count++
            textView.setOnClickListener{
                linearLayout.removeView(textView)
            }
        }

    }
}