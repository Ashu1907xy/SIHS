package com.example.weatheropenapi.DataModels



data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
)