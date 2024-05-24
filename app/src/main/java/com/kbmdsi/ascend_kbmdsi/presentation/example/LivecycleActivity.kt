package com.kbmdsi.ascend_kbmdsi.presentation.example

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kbmdsi.ascend_kbmdsi.R
import com.kbmdsi.ascend_kbmdsi.presentation.note_screen.HomeActivity

class LivecycleActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("lifecycle","onCreate")
        Toast.makeText(applicationContext,"onCreate",Toast.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.lifecycle_text).setText("onCreate")

        findViewById<Button>(R.id.destroy_button).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.pause_button).setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
            startActivity(it)
        }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart")
        Toast.makeText(applicationContext,"onStart",Toast.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.lifecycle_text).setText("onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume")
        Toast.makeText(applicationContext,"onResume",Toast.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.lifecycle_text).setText("onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause")
        Toast.makeText(applicationContext,"onPause",Toast.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.lifecycle_text).setText("onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop")
        Toast.makeText(applicationContext,"onStop",Toast.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.lifecycle_text).setText("onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("lifecycle","onDestroy")
        Toast.makeText(applicationContext,"onDestroy",Toast.LENGTH_SHORT).show()
        findViewById<TextView>(R.id.lifecycle_text).setText("onDestroy")
    }
}