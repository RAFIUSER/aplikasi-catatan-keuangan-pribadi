package com.example.finmate.ui.screen.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finmate.data.model.ExpenseCategory
import com.example.finmate.ui.theme.*

val categoryIcons = mapOf(
    "Transportasi" to Icons.Default.Commute,
    "Makanan" to Icons.Default.Fastfood,
    "Belanja" to Icons.Default.ShoppingCart,
    "Minuman" to Icons.Default.LocalCafe,
    "Obat-obatan" to Icons.Default.MedicalServices,
    "Skincare" to Icons.Default.Face
)

@Composable
fun ExpenseItem(category: ExpenseCategory, total: Float) {
    // Ikon berdasarkan nama, default ke 'Help' jika tidak ketemu
    val icon = categoryIcons[category.name] ?: Icons.Default.Help

    // Menghitung persentase untuk progress bar
    val progress = category.amount / total

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ikon Bulat
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(category.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = category.name,
                    tint = category.color
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Nama Kategori dan Progress Bar
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = category.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = progress,
                    color = category.color,
                    trackColor = Color.Gray.copy(alpha = 0.2f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Jumlah Uang
            Text(
                text = "Rp ${"%,.0f".format(category.amount)}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExpenseItem() {
    val sampleCategory = ExpenseCategory(
        name = "Transportasi",
        amount = 50000f,
        color = RedTransport
    )
    ExpenseItem(category = sampleCategory, total = 200000f)
}