package com.example.androidapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidapp.kotlinegs.LoginManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun loginClick(view: View) {
        Log.i("loginClick","loginClicked")
        var loginmanager:LoginManager=LoginManager()
        var username:TextView=findViewById(R.id.LoginUserName)
        var password:TextView=findViewById(R.id.editTextNumberPassword)
        if(loginmanager.isValidLogin(username.text.toString(),password.text.toString())) {
            var hIntent: Intent = Intent(this, HomeActivity::class.java)
            val data: TextView = findViewById(R.id.LoginUserName)
            hIntent.putExtra("nkey", data.text.toString())
            startActivity(hIntent)
        }
    }
}