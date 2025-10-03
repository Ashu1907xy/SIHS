package com.example.smart.Authentation.Screen


import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart.Authentation.ViewModel.AuthState
import com.example.smart.Authentation.ViewModel.AuthViewModel
import com.example.smart.R
import kotlinx.coroutines.delay


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginSignScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    var isLogin by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when (authState.value) {
            // change
            is AuthState.Authenticated -> navController.navigate("multi")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))
        // Title
//        Icon(
//            painterResource(R.drawable.tactor),
//            contentDescription = null,
//            modifier = Modifier.size(150.dp)
//        )
        Image(
            painter = painterResource(R.drawable.tactor),
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
        )


        Spacer(modifier = Modifier.height(15.dp))


        Text(
            text = "Kishan Sathi",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFE3F2FD))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            isLogin = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isLogin) Color(0xFF1565C0) else Color.Transparent,
                            contentColor = if (isLogin) Color.White else Color.Black
                        ),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Log In")
                    }

                    Button(
                        onClick = { isLogin = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!isLogin) Color(0xFF1565C0) else Color.Transparent,
                            contentColor = if (!isLogin) Color.White else Color.Black
                        ),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(50),

                        ) {
                        Text("Sign In")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Username or Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Submit Button
                Button(
                    onClick = {
                        if (isLogin == true) {
                            authViewModel.login(email, password)
                        } else {
                            authViewModel.signup(email, password)
                            navController.navigate("authScreen")
                        }
                    }, enabled = authState.value != AuthState.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = if (isLogin) "Log In" else "Sign In", fontSize = 16.sp)
                }

            }
        } // card

        Spacer(modifier = Modifier.height(16.dp))

        Text("or", color = Color.DarkGray)

        Spacer(modifier = Modifier.height(12.dp))


        // Social Icons Row
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            IconButton(onClick = { /* Facebook */ }) {
                Icon(Icons.Default.Email, contentDescription = "Facebook", tint = Red)
            }
            IconButton(onClick = { /* Twitter */ }) {
                Icon(Icons.Default.MailOutline, contentDescription = "Google", tint = Red)
            }
            IconButton(onClick = { /* Google */ }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Twitter",
                    tint = Color(0xFF1DA1F2)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Privacy policy · Term of service",
            color = Color.DarkGray,
            fontSize = 14.sp
        )
    }
}