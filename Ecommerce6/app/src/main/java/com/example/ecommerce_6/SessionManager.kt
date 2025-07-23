package com.example.ecommerce_6

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val PREF_NAME = "UserSession"
    private val KEY_TOKEN = "jwt_token"
    private val KEY_USER_EMAIL = "user_email" // Example: store user email for display

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun saveAuthToken(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    fun saveUserEmail(email: String) {
        editor.putString(KEY_USER_EMAIL, email)
        editor.apply()
    }

    fun fetchUserEmail(): String? {
        return prefs.getString(KEY_USER_EMAIL, null)
    }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}