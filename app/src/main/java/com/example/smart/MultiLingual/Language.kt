package com.example.smart.MultiLingual


data class Language(
    val code: String,
    val name: String,
    val flag: String
) {
    companion object {
        val HINDI = Language("hi", "हिंदी", "🇮🇳")
        val ENGLISH = Language("en", "English", "🇮🇳")
        val TAMIL = Language("ta", "தமிழ்", "🇮🇳")

        val PUNJABI = Language("pa", "ਪੰਜਾਬੀ", "🇮🇳")

        val MARATHI = Language("mr", "मराठी", "🇮🇳")

        val availableLanguages = listOf(HINDI, ENGLISH, TAMIL, PUNJABI , MARATHI)

        fun getLanguageByCode(code: String): Language {
            return availableLanguages.find { it.code == code } ?: ENGLISH
        }
    }
}
