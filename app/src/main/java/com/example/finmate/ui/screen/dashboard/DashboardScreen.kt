// com.example.finmate.ui.screen.dashboard/DashboardScreen.kt
package com.example.finmate.ui.screen.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book // FIX: Ikon Pencatatan
import androidx.compose.material.icons.filled.Home // FIX: Ikon Home
import androidx.compose.material.icons.filled.MoreVert // FIX: Ikon Titik Tiga
import androidx.compose.material.icons.filled.Person // FIX: Ikon Laporan/Profil
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finmate.data.model.ExpenseCategory
import com.example.finmate.ui.screen.dashboard.components.*
import com.example.finmate.ui.theme.* // Data dummy untuk UI
val dummyCategories = listOf(
    ExpenseCategory("Transportasi", 50000f, RedTransport),
    ExpenseCategory("Makanan", 150000f, GreenFood),
    ExpenseCategory("Belanja", 80000f, BlueShopping),
    ExpenseCategory("Obat-obatan", 30000f, TealBills),
    ExpenseCategory("Skincare", 45000f, PurpleDrinks)
)

// Menghitung total untuk progress bar
val totalExpense = dummyCategories.sumOf { it.amount.toDouble() }.toFloat()

// --- KOMPONEN TOP APP BAR (Sesuai Revisi Sketsa) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Dashboard",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        actions = {
            // Ikon Titik Tiga (Setting/Profile)
            IconButton(onClick = { /* TODO: Aksi Setting/Profile */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Setting/Profile",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,

        // --- TOP BAR BARU ---
        topBar = { DashboardTopAppBar() },

        // --- BOTTOM NAVIGATION BAR BARU ---
        bottomBar = {
            NavigationBar(containerColor = FinmateLightYellow) {
                // Item 1: Home
                NavigationBarItem(
                    selected = true, onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") }, label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = FinmateYellow, selectedTextColor = FinmateYellow)
                )
                // Item 2: Pencatatan (Ikon Buku)
                NavigationBarItem(
                    selected = false, onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Default.Book, contentDescription = "Pencatatan") }, label = { Text("Pencatatan") }
                )
                // Item 3: Laporan (Ikon Person)
                NavigationBarItem(
                    selected = false, onClick = { /*TODO*/ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Laporan") }, label = { Text("Laporan") }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = FinmateYellow, // FIX: Menggunakan FinmateYellow
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { paddingValues ->
        // LazyColumn konten desain awal
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item { TotalExpenseCard() }
            item { MonthlyExpenseChart(categories = dummyCategories) }

            item {
                Text(
                    text = "Daftar Pengeluaran",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            items(dummyCategories) { category ->
                ExpenseItem(category = category, total = totalExpense)
            }
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    FinmateTheme {
        DashboardScreen()
    }
}