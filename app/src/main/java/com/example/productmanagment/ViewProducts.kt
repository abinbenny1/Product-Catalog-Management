package com.example.productmanagment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ViewProducts : AppCompatActivity() {

    private lateinit var dbHelper: ProductDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_products)

        dbHelper = ProductDatabaseHelper(this)
        val products = dbHelper.getAllProducts()

        val listView = findViewById<ListView>(R.id.listproduct)

        // Check if products list is empty
        if (products.isEmpty()) {
            Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show()
            return // Exit if there are no products
        }

        val myListAdapter = MyListAdapter(this, ArrayList(products))
        listView.adapter = myListAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            adapterView.getChildAt(position)?.setBackgroundColor(Color.rgb(53, 177, 212))

            val selectedProduct = products[position]
            Toast.makeText(applicationContext, "Selected: ${selectedProduct.name}", Toast.LENGTH_SHORT).show()

            // Navigate to UpdateProduct Activity
            val updateIntent = Intent(this, UpdateProduct::class.java).apply {
                putExtra("product_name", selectedProduct.name)
                putExtra("product_price", selectedProduct.price)
                putExtra("product_quantity", selectedProduct.quantity)
            }
            startActivity(updateIntent)
        }

        listView.setOnItemLongClickListener { adapterView, view, position, id ->
            val selectedProduct = products[position]

            // Navigate to DeleteProduct Activity
            val deleteIntent = Intent(this, DeleteProduct::class.java).apply {
                putExtra("product_name", selectedProduct.name)
            }
            startActivity(deleteIntent)

            true
        }

        // Add back button functionality
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
    }
}
