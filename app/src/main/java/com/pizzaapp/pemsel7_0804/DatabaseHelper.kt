package com.pizzaapp.pemsel7_0804

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "PizzaApp.db"
        private const val DATABASE_VERSION = 1

        // Table Users
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsers = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()

        db?.execSQL(createTableUsers)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Function to check if email already exists
    fun checkData(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    // Function to add new account
    fun addAccount(name: String, email: String, password: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_NAME, name)
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)

        val result = db.insert(TABLE_USERS, null, values)
        db.close()

        return if (result != -1L) {
            Toast.makeText(context, "Registrasi berhasil! Silahkan login dengan akun Anda.", Toast.LENGTH_LONG).show()
            true
        } else {
            Toast.makeText(context, "Registrasi gagal! Silahkan coba lagi.", Toast.LENGTH_SHORT).show()
            false
        }
    }

    // Function to check login credentials
    fun checkLogin(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password)
        )
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    // Function to get user name by email
    fun getUserName(email: String): String {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_NAME FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        var name = ""
        if (cursor.moveToFirst()) {
            name = cursor.getString(0)
        }
        cursor.close()
        db.close()
        return name
    }
}