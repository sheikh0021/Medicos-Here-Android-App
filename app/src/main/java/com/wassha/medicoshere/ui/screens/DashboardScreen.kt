package com.wassha.medicoshere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wassha.medicoshere.ui.theme.MedicosHereTheme

data class Medicine(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val category: String,
    val isAvailable: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onLogout: () -> Unit
) {
    val medicines = remember {
        listOf(
            Medicine(1, "Paracetamol", "Pain reliever and fever reducer", "$5.99", "Pain Relief"),
            Medicine(2, "Ibuprofen", "Anti-inflammatory pain medication", "$7.99", "Pain Relief"),
            Medicine(3, "Amoxicillin", "Antibiotic for bacterial infections", "$12.99", "Antibiotics"),
            Medicine(4, "Omeprazole", "Reduces stomach acid production", "$15.99", "Digestive Health"),
            Medicine(5, "Cetirizine", "Antihistamine for allergies", "$8.99", "Allergy"),
            Medicine(6, "Vitamin D3", "Supports bone health and immunity", "$9.99", "Vitamins"),
            Medicine(7, "Metformin", "Diabetes medication", "$18.99", "Diabetes"),
            Medicine(8, "Lisinopril", "Blood pressure medication", "$14.99", "Cardiovascular"),
            Medicine(9, "Atorvastatin", "Cholesterol-lowering medication", "$22.99", "Cardiovascular"),
            Medicine(10, "Sertraline", "Antidepressant medication", "$16.99", "Mental Health")
        )
    }
    
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    
    val categories = listOf("All", "Pain Relief", "Antibiotics", "Digestive Health", "Allergy", "Vitamins", "Diabetes", "Cardiovascular", "Mental Health")
    
    val filteredMedicines = medicines.filter { medicine ->
        val matchesSearch = medicine.name.contains(searchQuery, ignoreCase = true) ||
                medicine.description.contains(searchQuery, ignoreCase = true)
        val matchesCategory = selectedCategory == "All" || medicine.category == selectedCategory
        matchesSearch && matchesCategory
    }
    
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
                        text = "Medicos Here",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold
                    ) 
                },
                actions = {
                    IconButton(onClick = onLogout) {
                                                                    Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color(0xFF2E7D32)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            
            // Welcome Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Welcome back!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Find your medicines and healthcare products",
                    fontSize = 16.sp,
                    color = Color(0xFF2E7D32).copy(alpha = 0.8f)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search medicines...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFF2E7D32).copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2E7D32),
                        unfocusedBorderColor = Color(0xFF2E7D32).copy(alpha = 0.7f),
                        focusedPlaceholderColor = Color(0xFF2E7D32).copy(alpha = 0.7f),
                        unfocusedPlaceholderColor = Color(0xFF2E7D32).copy(alpha = 0.7f),
                        cursorColor = Color(0xFF2E7D32),
                        focusedTextColor = Color(0xFF2E7D32),
                        unfocusedTextColor = Color(0xFF2E7D32),
                        focusedLeadingIconColor = Color(0xFF2E7D32),
                        unfocusedLeadingIconColor = Color(0xFF2E7D32).copy(alpha = 0.7f)
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Category Filter
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF2E7D32),
                                selectedLabelColor = Color.White,
                                containerColor = Color.White.copy(alpha = 0.8f),
                                labelColor = Color(0xFF2E7D32)
                            )
                        )
                    }
                }
            }
            
            // Medicines List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
                    .padding(top = 24.dp),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Available Medicines",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "${filteredMedicines.size} medicines found",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                items(filteredMedicines) { medicine ->
                    MedicineCard(medicine = medicine)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCard(medicine: Medicine) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Medicine Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Color(0xFFE8F5E8),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Medicine",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(32.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Medicine Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = medicine.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = medicine.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = medicine.price,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = "•",
                        color = Color.Gray
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = medicine.category,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .background(
                                color = Color(0xFFF5F5F5),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            
            // Action Button
            IconButton(
                onClick = { /* Add to cart or view details */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add to cart",
                    tint = Color(0xFF2E7D32)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    MedicosHereTheme {
        DashboardScreen(
            onLogout = {}
        )
    }
}
