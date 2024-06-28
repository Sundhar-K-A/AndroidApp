package com.example.studentmanagement

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                showNotification(student)
//                val intent = Intent(this, HomeActivity::class.java)
//                Log.d("AddStudentActivity", "Student added: $student")
//                startActivity(intent)
            }
        }
    }
    fun showNotification(student: Student) {
        createNotificationChannel()
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(this, "New Student")
            .setSmallIcon(R.drawable.baseline_person_24)
            .setContentTitle("Student Management")
            .setContentText("New student added ${student.studentName}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
        var notifManager: NotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifManager.notify(12,builder.build())
    }
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Student Management"
            val descriptionText = "Student added"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("New Student", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

    }

}