package com.example.smart.MultiLingual


import android.content.Context
import java.util.*

class LanguageManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("app_language", Context.MODE_PRIVATE)

    fun setLanguage(languageCode: String) {
        prefs.edit().putString("selected_language", languageCode).apply()
    }

    fun getLanguage(): String {
        return prefs.getString("selected_language", "en") ?: "en"
    }

    fun applyLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun initializeLanguage() {
        val savedLanguage = getLanguage()
        applyLanguage(savedLanguage)
    }
}
