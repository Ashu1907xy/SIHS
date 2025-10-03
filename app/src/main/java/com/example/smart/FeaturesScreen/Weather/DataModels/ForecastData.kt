package com.example.weatheropenapi.DataModels

data class ForecastData(
    val day: String,
    val maxTemp: Int,
    val minTemp: Int,
    val icon: String
)