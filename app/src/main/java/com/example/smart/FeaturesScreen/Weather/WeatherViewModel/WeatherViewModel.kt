package com.example.smart.FeaturesScreen.Weather.WeatherViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smart.FeaturesScreen.Weather.Services.WeatherApiService
import com.example.weatheropenapi.DataModels.ForecastData
import com.example.weatheropenapi.DataModels.WeatherData

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

class WeatherViewModel : ViewModel() {
    private val apiKey = "53beca00384c73f26e138995693435f1"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(WeatherApiService::class.java)

    private val _currentWeather = MutableStateFlow<WeatherData?>(null)
    val currentWeather: StateFlow<WeatherData?> = _currentWeather

    private val _forecast = MutableStateFlow<List<ForecastData>>(emptyList())
    val forecast: StateFlow<List<ForecastData>> = _forecast

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory: StateFlow<List<String>> = _searchHistory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getWeather("Bhopal") // Default city
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                // Add to search history
                val history = _searchHistory.value.toMutableList()
                history.remove(city)
                history.add(0, city)
                _searchHistory.value = history.take(10)

                // Fetch current weather from API
                val currentResponse = apiService.getCurrentWeather(city, apiKey)

                _currentWeather.value = WeatherData(
                    cityName = currentResponse.name,
                    temp = currentResponse.main.temp.roundToInt(),
                    feelsLike = currentResponse.main.feels_like.roundToInt(),
                    description = currentResponse.weather.firstOrNull()?.description ?: "",
                    weatherIcon = getWeatherIcon(
                        currentResponse.weather.firstOrNull()?.main ?: "Clear"
                    ),
                    humidity = currentResponse.main.humidity,
                    windSpeed = currentResponse.wind.speed,
                    pressure = currentResponse.main.pressure,
                    date = SimpleDateFormat("EEEE, MMM dd", Locale.getDefault())
                        .format(Date(currentResponse.dt * 1000))
                )

                // Fetch 5-day forecast from API
                val forecastResponse = apiService.getForecast(city, apiKey)

                // Group forecasts by day and take one per day (noon forecast)
                val dailyForecasts = forecastResponse.list
                    .groupBy {
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            .format(Date(it.dt * 1000))
                    }
                    .map { (_, items) ->
                        // Take the noon forecast (12:00) or closest to it
                        items.minByOrNull { item ->
                            val hour = SimpleDateFormat("HH", Locale.getDefault())
                                .format(Date(item.dt * 1000)).toInt()
                            abs(hour - 12)
                        } ?: items.first()
                    }
                    .take(5)

                _forecast.value = dailyForecasts.map { item ->
                    ForecastData(
                        day = SimpleDateFormat("EEE", Locale.getDefault())
                            .format(Date(item.dt * 1000)),
                        maxTemp = item.main.temp_max.roundToInt(),
                        minTemp = item.main.temp_min.roundToInt(),
                        icon = getWeatherIcon(item.weather.firstOrNull()?.main ?: "Clear")
                    )
                }

            } catch (e: Exception) {
                _errorMessage.value = "City not found or network error"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCurrentLocationWeather() {
        // In real app, get location using LocationManager
        getWeather("Mumbai") // Placeholder for location-based search
    }

    private fun getWeatherIcon(condition: String): String {
        return when (condition) {
            "Clear" -> "☀️"
            "Clouds" -> "☁️"
            "Rain", "Drizzle" -> "🌧️"
            "Snow" -> "❄️"
            "Thunderstorm" -> "⛈️"
            "Mist", "Fog", "Haze" -> "🌫️"
            else -> "🌤️"
        }
    }
}