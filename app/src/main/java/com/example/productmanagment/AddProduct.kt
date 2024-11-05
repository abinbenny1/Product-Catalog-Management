package com.example.productmanagment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProduct : AppCompatActivity() {

    private lateinit var dbHelper: ProductDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        dbHelper = ProductDatabaseHelper(this)

        // Reference the buttons
        val addButton: Button = findViewById(R.id.add)
        val backButton: Button = findViewById(R.id.backButton)

        // Add product button click listener
        addButton.setOnClickListener {
            val name = productname.text.toString().trim()
            val priceInput = price.text.toString().trim()
            val quantityInput = quantity.text.toString().trim()

            // Convert price and quantity to integers safely
            val price = priceInput.toIntOrNull()
            val quantity = quantityInput.toIntOrNull()

            if (name.isNotEmpty() && price != null && quantity != null) {
                val product = Product(name, price, quantity)
                dbHelper.addProduct(product)

                Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()

                // Navigate to ViewProducts activity
                val intent = Intent(this, ViewProducts::class.java)
                startActivity(intent)
                finish() // Close this activity if needed
            } else {
                Toast.makeText(this, "Please enter valid product details", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button click listener
        backButton.setOnClickListener {
            // Create an intent to navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
    }
}
