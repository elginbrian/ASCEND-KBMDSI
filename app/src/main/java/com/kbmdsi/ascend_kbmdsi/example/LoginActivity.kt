package com.kbmdsi.ascend_kbmdsi.example

import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.kbmdsi.ascend_kbmdsi.R

class LoginActivity(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var email = findViewById<EditText>(R.id.etEmail)
        var password = findViewById<EditText>(R.id.etPassword)
        var login = findViewById<CardView>(R.id.login)

        var emailString = email.text.toString()
        var passwordString = password.text.toString()

        email.setOnKeyListener { _, keyCode, event ->
            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                emailString = email.text.toString()
                true
            } else {
                false
            }
        }

        password.setOnKeyListener { _, keyCode, event ->
            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                passwordString = password.text.toString()
                true
            } else {
                false
            }
        }

        login.setOnClickListener {
            if(passwordString.length >= 8){
                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
            }
        }
    }
}