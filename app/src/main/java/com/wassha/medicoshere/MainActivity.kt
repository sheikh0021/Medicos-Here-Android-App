package com.wassha.medicoshere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wassha.medicoshere.ui.screens.LoginScreen
import com.wassha.medicoshere.ui.screens.SignupScreen
import com.wassha.medicoshere.ui.screens.WelcomeScreen
import com.wassha.medicoshere.ui.theme.MedicosHereTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicosHereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MedicosHereApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MedicosHereApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "welcome",
        modifier = modifier
    ) {
        composable("welcome") {
            WelcomeScreen(
                onSignupClick = { navController.navigate("signup") },
                onLoginClick = { navController.navigate("login") }
            )
        }
        composable("signup") {
            SignupScreen(
                onBackClick = { navController.popBackStack() },
                onSignupSuccess = { navController.navigate("login") }
            )
        }
        composable("login") {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onLoginSuccess = { /* Navigate to main app */ }
            )
        }
    }
}