package com.example.ecommerce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        val jsonString = intent.getStringExtra("item")
        val gson = Gson()
        val item = gson.fromJson(jsonString, Item::class.java)

        item_name.text = item.name
        item_description.text = item.description
        item_price.text = "$${item.price}"

        add_to_cart_button.setOnClickListener {

            // Save cart to shared preferences
            val sharedPrefs = getSharedPreferences("Cart", Context.MODE_PRIVATE)
            val editor = sharedPrefs.edit()

            editor.putString("cart", jsonString)
            editor.apply()

            // Return to dashboard
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}