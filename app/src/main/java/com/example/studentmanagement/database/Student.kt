package com.example.studentmanagement.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = false)
    val regno: String,
    @ColumnInfo(name = "name")
    val studentName: String,
    @ColumnInfo(name = "email")
    val studentEmail: String,
    @ColumnInfo(name = "cgpa")
    val studentCGPA: Double
)
