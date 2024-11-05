package com.example.productmanagment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateProduct : AppCompatActivity() {

    private lateinit var productNameEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var quantityEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var backButton: Button
    private lateinit var dbHelper: ProductDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        dbHelper = ProductDatabaseHelper(this)

        productNameEditText = findViewById(R.id.productname)
        priceEditText = findViewById(R.id.price)
        quantityEditText = findViewById(R.id.quantity)
        updateButton = findViewById(R.id.update)
        backButton = findViewById(R.id.backButton)

        // Update button click listener
        updateButton.setOnClickListener {
            val name = productNameEditText.text.toString().trim()
            val price = priceEditText.text.toString().toIntOrNull()
            val quantity = quantityEditText.text.toString().toIntOrNull()

            if (name.isNotEmpty() && price != null && quantity != null) {
                val product = Product(name, price, quantity)
                val result = dbHelper.updateProduct(product)

                if (result > 0) {
                    Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show()
                    // After successful update, navigate back to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Close current activity
                } else {
                    Toast.makeText(this, "Failed to update product", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter valid product details", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button click listener
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
