package com.example.ecommerce

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Get the SharedPreferences instance
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        // Set a click listener on the register button
        button_register.setOnClickListener {
            val fullName = edit_text_full_name.text.toString()
            val email = edit_text_email.text.toString()
            val password = edit_text_password.text.toString()

            // Check if the user has entered all the required fields
            if (fullName.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all the required fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save the user's information to SharedPreferences
                val editor = preferences.edit()
                editor.putString("fullName", fullName)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                // Navigate to the main activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}