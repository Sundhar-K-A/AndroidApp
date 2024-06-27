package com.example.studentmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement.R
import com.example.studentmanagement.database.Student

class StudentAdapter(private val onClick: (Student) -> Unit) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_view, parent, false)
        return StudentViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        holder.bind(student)
    }

    class StudentViewHolder(itemView: View,private val onClick: (Student) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        private var currentStudent: Student? = null
        init {
            itemView.setOnClickListener {
                currentStudent?.let {student ->
                    onClick(student)
                }
            }
        }

        fun bind(student: Student) {
            currentStudent = student
            tvName.text = student.studentName
            tvEmail.text = student.studentEmail
        }
    }

    // Implement DiffCallback for efficient list updates
    private class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.regno == newItem.regno
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }
}
