package com.example.productmanagment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteProduct : AppCompatActivity() {

    private lateinit var productNameEditText: EditText
    private lateinit var deleteButton: Button
    private lateinit var backButton: Button
    private lateinit var dbHelper: ProductDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_product)

        dbHelper = ProductDatabaseHelper(this)

        productNameEditText = findViewById(R.id.productname)
        deleteButton = findViewById(R.id.delete)
        backButton = findViewById(R.id.backButton)

        // Delete button click listener
        deleteButton.setOnClickListener {
            val name = productNameEditText.text.toString().trim()

            if (name.isNotEmpty()) {
                val result = dbHelper.deleteProduct(name)

                if (result > 0) {
                    Toast.makeText(this, "Product deleted successfully", Toast.LENGTH_SHORT).show()
                    // After successful delete, navigate back to MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Close current activity
                } else {
                    Toast.makeText(this, "Failed to delete product", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a product name", Toast.LENGTH_SHORT).show()
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
