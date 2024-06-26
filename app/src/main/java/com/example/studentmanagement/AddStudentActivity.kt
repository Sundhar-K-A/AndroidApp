package com.example.studentmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagement.application.StudentsApplication
import com.example.studentmanagement.database.Student
import com.example.studentmanagement.databinding.ActivityAddStudentBinding
import com.example.studentmanagement.viewmodel.StudentViewModel
import com.example.studentmanagement.viewmodel.studentViewModelFactory

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding
    private val studentViewModel: StudentViewModel by viewModels {
        studentViewModelFactory((application as StudentsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAdd.setOnClickListener {
            val name = binding.tvName.text.toString()
            val regno = binding.tvRegno.text.toString()
            val email = binding.tvEmail.text.toString()
            val cgpa = binding.tvCGPA.text.toString().toDoubleOrNull()
            if (name.isNotEmpty() && regno.isNotEmpty() && email.isNotEmpty() && cgpa != null) {
                val student = Student(regno, name, email, cgpa);
                studentViewModel.insert(student)
                val intent = Intent(this, HomeActivity::class.java)
                Log.d("AddStudentActivity", "Student added: $student")
                startActivity(intent)
            }
        }
    }

}