package com.example.smart.FeaturesScreen.Weather.DataModels

import com.example.weatheropenapi.DataModels.Main
import com.example.weatheropenapi.DataModels.Weather
import com.example.weatheropenapi.DataModels.Wind


data class CurrentWeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val dt: Long
)