package com.example.studentmanagement.database


import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class StudentRespository(private val studentDao: StudentDao) {
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }
}