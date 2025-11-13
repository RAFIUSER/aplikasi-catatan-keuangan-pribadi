package com.example.finmate.ui.screen.laporan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LaporanScreen(modifier: Modifier = Modifier) {
    Column {
        // Contoh
        Text(text = "Hello world", fontStyle = FontStyle.Italic)
        Text(text = "Hello 2", fontStyle = FontStyle.Italic)
    }
}

@Preview(showBackground = true)
@Composable
private fun LaporanScreenPreview() {
    LaporanScreen()
}