    package com.example.studentmanagement.viewmodel

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import com.example.studentmanagement.database.Student
    import com.example.studentmanagement.database.StudentRespository
    import androidx.lifecycle.viewModelScope
    import androidx.lifecycle.asLiveData
    import kotlinx.coroutines.launch


    class StudentViewModel(private val repository: StudentRespository) : ViewModel() {

        val allStudent: LiveData<List<Student>> = repository.allStudents.asLiveData()
        fun insert(student: Student) = viewModelScope.launch {
            repository.insert(student)
        }
    }

    class studentViewModelFactory(private val repository: StudentRespository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StudentViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
