package com.example.weatheropenapi.DataModels

data class WeatherData(
    val cityName: String,
    val temp: Int,
    val feelsLike: Int,
    val description: String,
    val weatherIcon: String,
    val humidity: Int,
    val windSpeed: Double,
    val pressure: Int,
    val date: String
)