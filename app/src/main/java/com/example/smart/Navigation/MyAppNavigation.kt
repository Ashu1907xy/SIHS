package com.example.smart.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart.Authentation.Screen.HomePage
import com.example.smart.Authentation.Screen.LoginSignScreen

import com.example.smart.Authentation.ViewModel.AuthViewModel
import com.example.smart.MultiLingual.LanguageManager
import com.example.smart.MultiLingual.SocialMediaApp
import com.example.smart.Screen_Start.SplashScreen

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel , languageManager: LanguageManager) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashRoutes", builder = {
        composable("splashRoutes") {
            SplashScreen(modifier , navController , authViewModel)
        }
        composable("loginRoutes") {
            LoginSignScreen(modifier,navController,authViewModel)
        }

        composable("home"){
            HomePage(modifier,navController,authViewModel)
        }
        composable ("multi"){
            SocialMediaApp(languageManager,navController , authViewModel)
        }

    }
    )
}

