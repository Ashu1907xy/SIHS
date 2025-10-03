package com.example.weatheropenapi.WeatherApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatheropenapi.DataModels.WeatherData

@Composable
fun CurrentWeatherCard(weather: WeatherData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D2D2D)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                weather.cityName,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                weather.date,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                weather.weatherIcon,
                style = MaterialTheme.typography.displayLarge
            )

            Text(
                "${weather.temp}°C",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                weather.description.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Feels like ${weather.feelsLike}°C",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
