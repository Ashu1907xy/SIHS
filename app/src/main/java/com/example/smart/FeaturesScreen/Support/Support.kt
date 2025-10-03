package com.example.smart.FeaturesScreen.Support

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Support() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Crop Recommendation") },
                actions = {
                    IconButton(onClick = { /* Language toggle */ }) {
                        Icon(Icons.Default.Language, contentDescription = "Language")
                    }
                    IconButton(onClick = { /* Dark Mode */ }) {
                        Icon(Icons.Default.DarkMode, contentDescription = "Theme")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Input fields
            Text("Enter Soil & Weather Data", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Soil Type") })
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Rainfall (mm)") })
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Temperature (°C)") })
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("pH Value") })

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { /* AI Recommendation Logic */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Get Recommendation")
            }

            Spacer(Modifier.height(16.dp))

            // Result card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Recommended Crop: Wheat", style = MaterialTheme.typography.titleLarge)
                    Text("Confidence: 87%", style = MaterialTheme.typography.bodyMedium)
                    Text("Reason: Suitable soil pH and rainfall", style = MaterialTheme.typography.bodySmall)
                }
            }

            Spacer(Modifier.height(16.dp))

            // Support section
            Text("Support", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { /* Help */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Help / FAQs")
            }
            Button(onClick = { /* Contact Expert */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Contact Expert")
            }
        }
    }
}
