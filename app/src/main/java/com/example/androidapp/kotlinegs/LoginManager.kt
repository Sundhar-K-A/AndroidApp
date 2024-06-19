package com.example.androidapp.kotlinegs

class LoginManager {
    fun isValidLogin(username: String, password: String): Boolean {
        return username.length>3 && password.length>=4
    }
}
