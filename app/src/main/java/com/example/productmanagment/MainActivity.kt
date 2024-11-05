package com.example.productmanagment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button to add a product
        val addProductButton = findViewById<Button>(R.id.btn_add_product)
        addProductButton.setOnClickListener {
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }

        // Button to view all products
        val viewProductsButton = findViewById<Button>(R.id.btn_view_products)
        viewProductsButton.setOnClickListener {
            val intent = Intent(this, ViewProducts::class.java)
            startActivity(intent)
        }

        // Button to update a product
        val updateProductButton = findViewById<Button>(R.id.btn_update_product)
        updateProductButton.setOnClickListener {
            val intent = Intent(this, UpdateProduct::class.java)
            startActivity(intent)
        }

        // Button to delete a product
        val deleteProductButton = findViewById<Button>(R.id.btn_delete_product)
        deleteProductButton.setOnClickListener {
            val intent = Intent(this, DeleteProduct::class.java)
            startActivity(intent)
        }
    }
}
