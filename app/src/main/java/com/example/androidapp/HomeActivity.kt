package com.example.androidapp

import android.os.Bundle
import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.kotlinegs.WordsViewAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var homeTextView: TextView
    lateinit var myRecyclerView: RecyclerView
    var dataArray= arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        homeTextView = findViewById(R.id.tvWelcomeHome)
        myRecyclerView=findViewById(R.id.recyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        var wordsAdapter = WordsViewAdapter(dataArray)
        myRecyclerView.adapter = wordsAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = intent.extras?.getString("nkey")
        homeTextView.text = "welcome $data"//Welcome the user
    }

}