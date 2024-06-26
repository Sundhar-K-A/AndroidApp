package com.example.studentmanagement

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanagement.adapter.StudentAdapter
import com.example.studentmanagement.application.StudentsApplication
import com.example.studentmanagement.databinding.ActivitySearchBinding
import com.example.studentmanagement.viewmodel.StudentViewModel
import com.example.studentmanagement.viewmodel.studentViewModelFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val studentViewModel: StudentViewModel by viewModels {
        studentViewModelFactory((application as StudentsApplication).repository)
    }
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = StudentAdapter()
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.adapter = adapter
        binding.btnSearch.setOnClickListener{
            val searchString = binding.tvSearch.text.toString()
            Log.d("SearchActivity", "Search string: $searchString")
            val tvSearchResultsText = "Search results for '$searchString'"
            binding.tvSearchResults.text = tvSearchResultsText
            studentViewModel.getStudentByData(searchString).observe(this) { students ->
                Log.d("Sea][rchActivity", "Search results: $students")
                students?.let { adapter.submitList(it) }
            }

        }
    }
}