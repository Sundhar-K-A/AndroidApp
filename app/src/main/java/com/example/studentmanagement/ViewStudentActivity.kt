package com.example.studentmanagement

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.databinding.ActivityViewStudentBinding

class ViewStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regno = intent.getStringExtra("STUDENT_REGNO")
        val name = intent.getStringExtra("STUDENT_NAME")
        val email = intent.getStringExtra("STUDENT_EMAIL")
        val cgpa = intent.getDoubleExtra("STUDENT_CGPA", 0.0)

        Log.d("ViewStudentActivity", "Regno: $regno, Name: $name, Email: $email, CGPA: $cgpa")

        binding.tvRegno.text = regno
        binding.tvName.text = name
        binding.tvEmail.text = email
        binding.tvCgpa.text = cgpa.toString()
    }
}
