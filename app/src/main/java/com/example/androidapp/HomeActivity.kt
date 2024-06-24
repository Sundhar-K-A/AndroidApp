package com.example.androidapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapp.databinding.ActivityHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.androidapp.database.Item
import com.example.androidapp.database.ItemDao
import com.example.androidapp.database.ItemRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first


class HomeActivity : AppCompatActivity() {
    private lateinit var homeTextView: TextView
    private lateinit var binding: ActivityHomeBinding
    lateinit var dao: ItemDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var database= ItemRoomDatabase.getDatabase(this)
        dao=database.itemDao()
        homeTextView = findViewById(R.id.tvWelcomeHome)//username importing


        binding.btnDbInsert.setOnClickListener{
            insertDataDb()
        }
        binding.btnFind.setOnClickListener{
            findItemDb(10)
        }
    }

    private fun findItemDb(id: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            var item = dao.getItem(id).first()
            binding.tvWelcomeHome.setText(item.itemName)
        }
    }

//        val data = intent.extras?.getString("nkey")
//        homeTextView.text = "welcome $data"//Welcome the user

    private fun insertDataDb() {
        GlobalScope.launch {
            var item = Item(10,"fruits",11.11,11)
            dao.insert(item)
        }
    }

}