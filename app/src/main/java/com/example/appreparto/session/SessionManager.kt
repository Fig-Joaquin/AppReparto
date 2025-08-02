package com.example.appreparto.session

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("appreparto_session", Context.MODE_PRIVATE)

    fun saveEmail(email: String) {
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)

    companion object {
        private const val KEY_EMAIL = "key_email"
    }
}
