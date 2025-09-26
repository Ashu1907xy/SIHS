package com.example.smart.Screen_Start
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionScreen() {
    var selectedLanguage by remember { mutableStateOf("English") }

    val languages = listOf(
        "English" to "Hi.",
        "Hindi" to "नमस्ते.",
        "Marathi" to "नमस्कार.",
        "Kannada" to "ಹಲೋ.",
        "Tamil" to "வணக்கம்.",
        "Telugu" to "హలో.",
        "Gujarati" to "નમસ્તે.",
        "Bengali" to "নমস্কার.",
        "Punjabi" to "ਸਤ ਸ੍ਰੀ ਅਕਾਲ.",
        "Malayalam" to "ഹലോ."
    )
    Scaffold(
       topBar = {
           TopAppBar(

                    title = {Text(
                        text = "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )},
           )


       }

    ) { innerPadding ->

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose your language",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Grid layout with 2 columns
        Column {
            for (row in languages.chunked(2)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEach { (language, greeting) ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            LanguageCard(
                                language = language,
                                greeting = greeting,
                                isSelected = selectedLanguage == language,
                                onClick = { selectedLanguage = language }
                            )
                        }
                    }
                    if (row.size == 1) {
                        Spacer(modifier = Modifier.weight(1f)) // balance if odd number
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Handle continue action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008060)), // green
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("CONTINUE", color = Color.White, fontSize = 16.sp)
        }
    }
}
}

@Composable
fun LanguageCard(
    language: String,
    greeting: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF008060)) else BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE8F5E9) else Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = greeting, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(text = language, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
