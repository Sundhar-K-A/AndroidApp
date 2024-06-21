package com.example.androidapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidapp.databinding.ActivityMainBinding // Import View Binding
import com.example.androidapp.kotlinegs.LoginManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Declare binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater) // Inflate binding
        setContentView(binding.root) // Set content view

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets -> // Use binding
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun loginClick(view: View) {
        Log.i("loginClick", "loginClicked")
        val loginManager = LoginManager()

        if (loginManager.isValidLogin(
                binding.LoginUserName.text.toString(), // Use binding
                binding.editTextNumberPassword.text.toString() // Use binding
            )
        ) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("nkey", binding.LoginUserName.text.toString()) // Use binding
            startActivity(intent)
        }
    }
}