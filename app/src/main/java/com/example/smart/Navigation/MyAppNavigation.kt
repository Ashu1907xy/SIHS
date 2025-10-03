package com.example.smart.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart.Authentation.Screen.LoginSignScreen

import com.example.smart.Authentation.ViewModel.AuthViewModel
import com.example.smart.MultiLingual.LanguageManager
import com.example.smart.Authentation.Screen.HomePage
import com.example.smart.DrawerScreens.About
import com.example.smart.DrawerScreens.Analytics
import com.example.smart.DrawerScreens.Favorite
import com.example.smart.DrawerScreens.Help
import com.example.smart.DrawerScreens.Inbox
import com.example.smart.DrawerScreens.MyFarm
import com.example.smart.DrawerScreens.Profile
import com.example.smart.DrawerScreens.Setting
import com.example.smart.FeaturesScreen.AiCropAdvisory.AiCropAdvisory
import com.example.smart.FeaturesScreen.Camera.Camera
import com.example.smart.FeaturesScreen.MarketPrice.MarketPrice
import com.example.smart.FeaturesScreen.News.News
import com.example.smart.FeaturesScreen.SoilReport.SoilReport
import com.example.smart.FeaturesScreen.SoilTest.SoilTest
import com.example.smart.FeaturesScreen.Support.Support
import com.example.smart.Screen_Start.SplashScreen
import com.example.weatheropenapi.WeatherApp.WeatherApp

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
        composable ("multi"){
            HomePage(languageManager,navController , authViewModel)
        }

            //Features Routes


        composable ("/ai-advisor"){
            AiCropAdvisory()
        }
        composable ("/camera"){
            Camera()
        }
        composable ("/market"){
            MarketPrice()
        }
        composable ("/news"){
            News()
        }
        composable ("/soil-report"){
            SoilReport()
        }
        composable ("/soil-test"){
            SoilTest()
        }
        composable ("/support"){
            Support()
        }
        composable ("/weather"){
            WeatherApp()
        }

        // DrawerRoutes

        composable ("Profile"){
            Profile()
        }
        composable ("Inbox"){
            Inbox()
        }
        composable ("MyFarm"){
            MyFarm()
        }
        composable ("Analytics"){
            Analytics()
        }
        composable ("Favorite"){
            Favorite()
        }
        composable ("Help"){
            Help()
        }
        composable ("About"){
            About()
        }
        composable ("Settings"){
            Setting()
        }

        //features App



    }
    )
}

