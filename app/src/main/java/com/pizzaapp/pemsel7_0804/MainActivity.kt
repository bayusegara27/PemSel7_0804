package com.pizzaapp.pemsel7_0804

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var tvUserInfo: TextView
    private lateinit var btnPizzaMenu: Button
    private lateinit var btnMyOrders: Button
    private lateinit var btnProfile: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupUserInfo()
        setupClickListeners()
    }

    private fun initViews() {
        tvWelcome = findViewById(R.id.tv_welcome)
        tvUserInfo = findViewById(R.id.tv_user_info)
        btnPizzaMenu = findViewById(R.id.btn_pizza_menu)
        btnMyOrders = findViewById(R.id.btn_my_orders)
        btnProfile = findViewById(R.id.btn_profile)
        btnLogout = findViewById(R.id.btn_logout)
    }

    private fun setupUserInfo() {
        val userName = intent.getStringExtra("USER_NAME") ?: "User"
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: ""

        tvWelcome.text = "Selamat Datang, $userName!"
        tvUserInfo.text = "Login sebagai: $userEmail"
    }

    private fun setupClickListeners() {
        btnPizzaMenu.setOnClickListener {
            // Intent ke PizzaMenuActivity (belum dibuat)
            // val intent = Intent(this, PizzaMenuActivity::class.java)
            // startActivity(intent)
        }

        btnMyOrders.setOnClickListener {
            // Intent ke MyOrdersActivity (belum dibuat)
            // val intent = Intent(this, MyOrdersActivity::class.java)
            // startActivity(intent)
        }

        btnProfile.setOnClickListener {
            // Intent ke ProfileActivity (belum dibuat)
            // val intent = Intent(this, ProfileActivity::class.java)
            // startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}