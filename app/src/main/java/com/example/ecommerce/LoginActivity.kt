package com.example.ecommerce

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize shared preferences
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        // Set onClickListener for login button
        button_login.setOnClickListener {
            val email = edit_text_email.text.toString().trim()
            val password = edit_text_password.text.toString().trim()

            // Check if email and password are valid
            if (isValidEmail(email) && isValidPassword(password)) {

                // Verify user credentials
                if (verifyUserCredentials(email, password)) {

                    // Save login status in shared preferences
                    val editor = preferences.edit()
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    // Redirect to main menu/dashboard
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // Credentials are invalid, show error message
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }

            } else {
                // Email or password is invalid, show error message
                Toast.makeText(this, "Email or password constraints", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Helper function to check if email is valid
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Helper function to check if password is valid
    private fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }
    private fun verifyUserCredentials(email: String, password: String): Boolean {
        val sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")

        return email == savedEmail && password == savedPassword
    }
    override fun onBackPressed() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}