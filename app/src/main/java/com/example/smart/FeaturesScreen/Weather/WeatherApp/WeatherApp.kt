package com.example.weatheropenapi.WeatherApp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smart.FeaturesScreen.Weather.WeatherViewModel.WeatherViewModel
import kotlin.collections.isNotEmpty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(viewModel: WeatherViewModel = viewModel()) {
    val currentWeather by viewModel.currentWeather.collectAsState()
    val forecast by viewModel.forecast.collectAsState()
    val searchHistory by viewModel.searchHistory.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var showHistory by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Forecast") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1E1E1E),
                            Color(0xFF2D2D2D)
                        )
                    )
                )
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Search Bar
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2D2D2D)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                value = searchQuery,
                                onValueChange = {
                                    searchQuery = it
                                    showHistory = it.isNotEmpty()
                                },
                                modifier = Modifier.weight(1f),
                                placeholder = { Text("Search city...") },
                                leadingIcon = { Icon(Icons.Default.Search, "Search") },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent
                                ),
                                singleLine = true
                            )
                            IconButton(
                                onClick = {
                                    if (searchQuery.isNotEmpty()) {
                                        viewModel.getWeather(searchQuery)
                                        showHistory = false
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Send, "Search")
                            }
                            IconButton(
                                onClick = { viewModel.getCurrentLocationWeather() }
                            ) {
                                Icon(Icons.Default.LocationOn, "Current Location")
                            }
                        }
                    }
                }

                // Search History
                if (showHistory && searchHistory.isNotEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF2D2D2D)
                            )
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    "Recent Searches",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(8.dp)
                                )
                                searchHistory.take(5).forEach { city ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                searchQuery = city
                                                viewModel.getWeather(city)
                                                showHistory = false
                                            }
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(Icons.Default.History, "History",
                                            modifier = Modifier.size(20.dp))
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(city)
                                    }
                                }
                            }
                        }
                    }
                }

                // Error Message
                errorMessage?.let { error ->
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFD32F2F).copy(alpha = 0.2f)
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Warning, "Error", tint = Color(0xFFEF5350))
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(error, color = Color(0xFFEF5350))
                            }
                        }
                    }
                }

                // Loading Indicator
                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                // Current Weather
                currentWeather?.let { weather ->
                    item {
                        CurrentWeatherCard(weather)
                    }
                }

                // Weather Details
                currentWeather?.let { weather ->
                    item {
                        WeatherDetailsCard(weather)
                    }
                }

                // 5-Day Forecast
                if (forecast.isNotEmpty()) {
                    item {
                        Text(
                            "5-Day Forecast",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(forecast) { day ->
                                ForecastCard(day)
                            }
                        }
                    }
                }
            }
        }
    }
}
