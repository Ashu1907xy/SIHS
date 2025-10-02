package com.example.smart.MultiLingual


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class multilingualViewModel : ViewModel() {
    private val _selectedLanguage = mutableStateOf(Language.ENGLISH)
    val selectedLanguage: State<Language> = _selectedLanguage

    private val _showLanguageDropdown = mutableStateOf(false)
    val showLanguageDropdown: State<Boolean> = _showLanguageDropdown

    private val _recompositionTrigger = mutableStateOf(0)
    val recompositionTrigger: State<Int> = _recompositionTrigger

    fun selectLanguage(language: Language) {
        _selectedLanguage.value = language
        _showLanguageDropdown.value = false
        _recompositionTrigger.value++
    }

    fun toggleLanguageDropdown() {
        _showLanguageDropdown.value = !_showLanguageDropdown.value
    }

    fun closeLanguageDropdown() {
        _showLanguageDropdown.value = false
    }

    fun initializeLanguage(languageCode: String) {
        _selectedLanguage.value = Language.getLanguageByCode(languageCode)
    }

}
