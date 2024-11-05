package com.example.productmanagment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ProductDatabase"
        private const val TABLE_PRODUCTS = "Products"

        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_PRICE = "price"
        private const val KEY_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_PRODUCTS ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$KEY_NAME TEXT, $KEY_PRICE INTEGER, $KEY_QUANTITY INTEGER)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    // Create (Insert)
    fun addProduct(product: Product): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, product.name)
        contentValues.put(KEY_PRICE, product.price)
        contentValues.put(KEY_QUANTITY, product.quantity)

        val success = db.insert(TABLE_PRODUCTS, null, contentValues)
        db.close()
        return success
    }

    // Read (Retrieve)
    fun getAllProducts(): List<Product> {
        val productList = ArrayList<Product>()
        val selectQuery = "SELECT * FROM $TABLE_PRODUCTS"
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val product = Product(
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_PRICE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_QUANTITY))
                    )
                    productList.add(product)
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return productList
    }

    // Update
    fun updateProduct(product: Product): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, product.name)
        contentValues.put(KEY_PRICE, product.price)
        contentValues.put(KEY_QUANTITY, product.quantity)

        val success = db.update(TABLE_PRODUCTS, contentValues, "$KEY_NAME = ?", arrayOf(product.name))
        db.close()
        return success
    }

    // Delete
    fun deleteProduct(name: String): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_PRODUCTS, "$KEY_NAME = ?", arrayOf(name))
        db.close()
        return success
    }
}
