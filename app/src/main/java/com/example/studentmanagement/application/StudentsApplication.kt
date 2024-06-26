package com.example.studentmanagement.application

import android.app.Application
import com.example.studentmanagement.database.StudentRespository
import com.example.studentmanagement.database.StudentRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class StudentsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { StudentRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StudentRespository(database.studentDao()) }
}