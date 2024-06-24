package com.example.androidapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp.database.Item
import com.example.androidapp.database.ItemDao
import com.example.androidapp.database.ItemRoomDatabase
import com.example.androidapp.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.androidapp.adapters.ItemAdapter
import android.util.Log
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class HomeActivity : AppCompatActivity() {
    private lateinit var homeTextView: TextView
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dao: ItemDao
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()
        homeTextView = findViewById(R.id.tvWelcomeHome)

        // Initialize RecyclerView and adapter
        itemAdapter = ItemAdapter(emptyList())
        binding.rvItems.adapter = itemAdapter
        binding.rvItems.layoutManager = LinearLayoutManager(this)

        binding.btnDbInsert.setOnClickListener {
            clearDatabase()
            insertDataDb()
            Log.d("HomeActivity", "Insert button clicked")
            lifecycleScope.launch {
                getAllItems()
            }
        }

        binding.btnFind.setOnClickListener {
            findItemDb(10)
        }
    }

    private fun findItemDb(id: Int) {
        lifecycleScope.launch {
            Log.d("HomeActivity", "Finding item with ID: $id")
            val item = withContext(Dispatchers.IO) {
                dao.getItem(id).firstOrNull()
            }
            withContext(Dispatchers.Main) {
                if (item != null) {
                    binding.tvWelcomeHome.text = item.itemName
                } else {
                    binding.tvWelcomeHome.text = "Item notfound"
                }
            }
        }
    }

    private fun insertDataDb() {
        lifecycleScope.launch {
//            val item=Item(10, "Fruits", 11.11, 10)
            val items = listOf(
                Item(10, "Fruits", 11.11, 10),
                Item(11, "Vegetables", 8.50, 15),
                Item(12, "Dairy", 5.00, 8),
                Item(13, "Grains", 3.25, 20),
                Item(14, "Meat", 12.75, 5),
                Item(15, "Snacks", 2.00, 30),
                Item(16, "Beverages", 1.50, 12),
                Item(17, "Spices", 4.75, 10),
                Item(18, "Bakery", 6.00, 7),
                Item(19, "Frozen Foods", 9.25, 18),
                Item(20, "Canned Goods", 2.50, 25)
            )
            withContext(Dispatchers.IO) {
                items.forEach { item ->
                    dao.insert(item)
                    Log.d("HomeActivity", "Inserted item: $item") // Log inserted item
                }
//                dao.insert(item)
//                Log.d("HomeActivity", "Inserted item: $item") // Log inserted item
            }
        }
    }
    private fun clearDatabase() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                dao.clearAllItems()
            }
        }
    }

    private suspend fun getAllItems() {
        Log.d("HomeActivity", "Fetching all items") // Log fetching attempt
        val items = withContext(Dispatchers.IO) {
            dao.getItems().first()
        }
        withContext(Dispatchers.Main) {
            itemAdapter.updateItems(items)
            Log.d("HomeActivity", "Updated RecyclerView with ${items.size} items") // Log update
        }
    }
}