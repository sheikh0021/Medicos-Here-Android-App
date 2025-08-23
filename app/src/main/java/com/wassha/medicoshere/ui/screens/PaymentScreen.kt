package com.wassha.medicoshere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class PaymentMethod {
    CASH_ON_DELIVERY,
    UPI,
    CARD
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    totalAmount: Double,
    onBackClick: () -> Unit,
    onPaymentComplete: () -> Unit
) {
    var selectedPaymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }
    var showPaymentDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE8F5E8), // Very light green
                        Color(0xFFF1F8E9), // Light mint
                        Color(0xFFF9FBE7)  // Light cream
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top App Bar
            TopAppBar(
                title = { 
                    Text(
                        text = "Payment",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF2E7D32)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Order Summary Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Order Summary",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                        Divider(color = Color(0xFFE8F5E8))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Total Amount",
                                color = Color.Black
                            )
                            Text(
                                "₹${String.format("%.2f", totalAmount)}",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }
                }

                // Payment Methods
                Text(
                    text = "Select Payment Method",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )

                // Cash on Delivery
                PaymentMethodCard(
                    title = "Cash on Delivery",
                    subtitle = "Pay when you receive your order",
                    icon = Icons.Filled.ShoppingCart,
                    isSelected = selectedPaymentMethod == PaymentMethod.CASH_ON_DELIVERY,
                    onClick = { selectedPaymentMethod = PaymentMethod.CASH_ON_DELIVERY }
                )

                // UPI Payment
                PaymentMethodCard(
                    title = "UPI Payment",
                    subtitle = "Pay using UPI apps (GPay, PhonePe, etc.)",
                    icon = Icons.Filled.Search,
                    isSelected = selectedPaymentMethod == PaymentMethod.UPI,
                    onClick = { selectedPaymentMethod = PaymentMethod.UPI }
                )

                // Card Payment
                PaymentMethodCard(
                    title = "Card Payment",
                    subtitle = "Credit/Debit card payment",
                    icon = Icons.Filled.Person,
                    isSelected = selectedPaymentMethod == PaymentMethod.CARD,
                    onClick = { selectedPaymentMethod = PaymentMethod.CARD }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Pay Button
                Button(
                    onClick = { showPaymentDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = selectedPaymentMethod != null,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32)
                    )
                ) {
                    Text(
                        text = "Pay ₹${String.format("%.2f", totalAmount)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }

    // Payment Processing Dialog
    if (showPaymentDialog) {
        PaymentProcessingDialog(
            paymentMethod = selectedPaymentMethod!!,
            onDismiss = { showPaymentDialog = false },
            onPaymentComplete = {
                showPaymentDialog = false
                onPaymentComplete()
            }
        )
    }
}

@Composable
fun PaymentMethodCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(
                2.dp, 
                Color(0xFF2E7D32)
            )
        } else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = if (isSelected) 
                    Color(0xFF2E7D32)
                else 
                    Color.Gray
            )
            
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) 
                        Color(0xFF2E7D32)
                    else 
                        Color.Black
                )
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = if (isSelected) 
                        Color(0xFF2E7D32).copy(alpha = 0.7f)
                    else 
                        Color.Gray
                )
            }
            
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Selected",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentProcessingDialog(
    paymentMethod: PaymentMethod,
    onDismiss: () -> Unit,
    onPaymentComplete: () -> Unit
) {
    var processingStep by remember { mutableStateOf(0) }
    
    LaunchedEffect(Unit) {
        // Simulate payment processing steps
        kotlinx.coroutines.delay(1000)
        processingStep = 1
        kotlinx.coroutines.delay(1000)
        processingStep = 2
        kotlinx.coroutines.delay(1000)
        processingStep = 3
        kotlinx.coroutines.delay(500)
        onPaymentComplete()
    }

    AlertDialog(
        onDismissRequest = { /* Don't allow dismiss during processing */ },
        title = {
            Text(
                text = "Processing Payment",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = Color(0xFF2E7D32)
                )
                
                Text(
                    text = when (processingStep) {
                        0 -> "Initializing payment..."
                        1 -> "Verifying payment method..."
                        2 -> "Processing transaction..."
                        3 -> "Payment successful!"
                        else -> "Completing order..."
                    },
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                
                if (processingStep == 3) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Success",
                        tint = Color.Green,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        confirmButton = {
            if (processingStep >= 3) {
                TextButton(onClick = onPaymentComplete) {
                    Text(
                        "Continue",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}
