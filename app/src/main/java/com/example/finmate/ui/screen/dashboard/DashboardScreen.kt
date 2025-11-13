package com.example.finmate.ui.screen.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finmate.data.model.ExpenseCategory
import com.example.finmate.ui.screen.dashboard.components.*
import com.example.finmate.ui.theme.*

// Data dummy untuk UI
val dummyCategories = listOf(
    ExpenseCategory("Transportasi", 50000f, RedTransport),
    ExpenseCategory("Makanan", 150000f, GreenFood),
    ExpenseCategory("Belanja", 80000f, BlueShopping),
    ExpenseCategory("Obat-obatan", 30000f, TealBills),
    ExpenseCategory("Skincare", 45000f, PurpleDrinks) // Mengganti "Minuman" jadi "Skincare"
)

// Menghitung total untuk progress bar
val totalExpense = dummyCategories.sumOf { it.amount.toDouble() }.toFloat()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            DashboardTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { paddingValues ->
        // LazyColumn agar bisa di-scroll dan efisien
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp), // Padding kiri-kanan
            verticalArrangement = Arrangement.spacedBy(16.dp), // Jarak antar item
            contentPadding = PaddingValues(vertical = 16.dp) // Padding atas-bawah
        ) {
            // Item 1: Total Pengeluaran
            item {
                TotalExpenseCard()
            }

            // Item 2: Chart Bulanan
            item {
                MonthlyExpenseChart(categories = dummyCategories)
            }

            // Item 3: Judul Daftar
            item {
                Text(
                    text = "Daftar Pengeluaran",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            // Item 4: List Pengeluaran
            items(dummyCategories) { category ->
                ExpenseItem(category = category, total = totalExpense)
            }
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen()
}