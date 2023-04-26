package com.example.ecommerce
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Fetch the list of items and populate the ListView
        val items = fetchItemsFromDatabase()
        val itemNames = items.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNames)
        item_list.adapter = adapter


        // Set up the onItemClick listener for the items in the list
        item_list.setOnItemClickListener { adapterView, _, i, _ ->
            // Get the selected item and display its details
            val selectedItemName = adapterView.getItemAtPosition(i) as String
            val selectedItem = items.find { it.name == selectedItemName }
            val intent = Intent(this@DashboardActivity, ItemDetailActivity::class.java)
            val gson = Gson()
            val jsonString = gson.toJson(selectedItem)
            intent.putExtra("item", jsonString)
            startActivity(intent)
        }
    }

    private fun fetchItemsFromDatabase(): Array<Item> {
        return arrayOf(
            Item("Item 1", "Item 1",10.0),
            Item("Item 2", "Item 2",15.0),
            Item("Item 3", "Item 3",20.0),
            Item("Item 4", "Item 4",30.0),
            Item("Item 5", "Item 5",40.0),
            Item("Item 5", "Item 6",50.0),
            Item("Item 6", "Item 7",60.0)
        )
    }
}
