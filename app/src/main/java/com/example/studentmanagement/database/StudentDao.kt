package com.example.studentmanagement.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudents()

    @Query("DELETE FROM student_table WHERE regno = :regno")
    suspend fun deleteStudentByRegno(regno: String)

    @Query("""SELECT * FROM student_table WHERE regno = :regno OR name = :name OR email = :email OR cgpa = :cgpa""")
    fun getStudentByData(regno: String?, name: String?, email: String?, cgpa: Double?): Flow<Student?>

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER BY regno ASC")
    fun getAllStudents(): Flow<List<Student>>
}
