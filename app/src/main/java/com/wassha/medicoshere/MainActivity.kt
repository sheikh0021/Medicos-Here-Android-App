package com.wassha.medicoshere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wassha.medicoshere.ui.screens.CartItem
import com.wassha.medicoshere.ui.screens.CartScreen
import com.wassha.medicoshere.ui.screens.DashboardScreen
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
    var cartItems by remember { mutableStateOf(listOf<CartItem>()) }
    
    NavHost(
        navController = navController,
        startDestination = "welcome",
        modifier = modifier
    ) {
        composable("welcome") {
            WelcomeScreen(
                onSignupClick = { navController.navigate("signup") },
                onLoginClick = { navController.navigate("login") },
                onSignupDirectClick = { navController.navigate("signup") }
            )
        }
        composable("signup") {
            SignupScreen(
                onBackClick = { navController.popBackStack() },
                onSignupSuccess = { navController.navigate("dashboard") }
            )
        }
        composable("login") {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onLoginSuccess = { navController.navigate("dashboard") },
                onSignupClick = { navController.navigate("signup") }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                onLogout = { 
                    navController.navigate("welcome") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onCartClick = { navController.navigate("cart") },
                cartItems = cartItems,
                onAddToCart = { medicine ->
                    val existingItem = cartItems.find { it.medicine.id == medicine.id }
                    if (existingItem != null) {
                        cartItems = cartItems.map { 
                            if (it.medicine.id == medicine.id) {
                                it.copy(quantity = it.quantity + 1)
                            } else {
                                it
                            }
                        }
                    } else {
                        cartItems = cartItems + CartItem(medicine)
                    }
                }
            )
        }
        composable("cart") {
            CartScreen(
                cartItems = cartItems,
                onBackClick = { navController.popBackStack() },
                onUpdateQuantity = { medicineId, newQuantity ->
                    cartItems = cartItems.map { item ->
                        if (item.medicine.id == medicineId) {
                            item.copy(quantity = newQuantity)
                        } else {
                            item
                        }
                    }
                },
                onRemoveItem = { medicineId ->
                    cartItems = cartItems.filter { it.medicine.id != medicineId }
                },
                onCheckout = {
                    // TODO: Implement checkout functionality
                    // For now, just clear the cart and go back to dashboard
                    cartItems = emptyList()
                    navController.navigate("dashboard") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
    }
}