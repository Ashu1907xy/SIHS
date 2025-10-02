package com.example.smart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.smart.Authentation.ViewModel.AuthViewModel
import com.example.smart.MultiLingual.LanguageManager
import com.example.smart.Navigation.MyAppNavigation
import com.example.smart.Screen_Start.SplashScreen
//import com.example.smart.Screen_Start.SplashScreen
import com.example.smart.ui.theme.SmartTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private lateinit var languageManager: LanguageManager
    private var isReady = false // new
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // new
        splashScreen.setKeepOnScreenCondition { !isReady } // new
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { // new
            languageManager = LanguageManager(this@MainActivity)
            languageManager.initializeLanguage()
            delay(2000)
            isReady = true
        }
        enableEdgeToEdge()
        setContent {
            val authViewModel : AuthViewModel by viewModels()
            SmartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(modifier = Modifier.padding(innerPadding) , authViewModel = authViewModel , languageManager = languageManager)
                }
            }
        }
    }
}