package com.example.weatheropenapi.DataModels

data class Main(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp_min: Double,
    val temp_max: Double
)