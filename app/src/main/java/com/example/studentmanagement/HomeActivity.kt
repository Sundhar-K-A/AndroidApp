package com.example.studentmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement.adapter.StudentAdapter
import com.example.studentmanagement.application.StudentsApplication
import com.example.studentmanagement.database.Student
import com.example.studentmanagement.viewmodel.StudentViewModel
import com.example.studentmanagement.viewmodel.studentViewModelFactory

class HomeActivity : AppCompatActivity() {
    private val studentViewModel: StudentViewModel by viewModels {
        studentViewModelFactory((application as StudentsApplication).repository)
    }
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.title = "Student Management"
        val recyclerView = findViewById<RecyclerView>(R.id.rvStudent)
        val onClick: (Student) -> Unit = { student ->
            Log.d("HomeActivity", "Student clicked: $student")
            val intent = Intent(this, ViewStudentActivity::class.java).apply {
                putExtra("STUDENT_REGNO", student.regno)
                putExtra("STUDENT_NAME", student.studentName)
                putExtra("STUDENT_EMAIL", student.studentEmail)
                putExtra("STUDENT_CGPA", student.studentCGPA)
            }
            startActivity(intent)
        }

        val adapter = StudentAdapter(onClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentViewModel.allStudent.observe(this) { words->
            words.let { adapter.submitList(it) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                val intent= Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_search -> {
                val intent= Intent(this, SearchActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
