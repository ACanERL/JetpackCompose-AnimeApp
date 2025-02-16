package com.example.anime_app_jetpackcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun CategoryChip(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = category,
        color = if (isSelected) Color.White else Color.Black,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) AppTheme.colorScheme.cardItemColor else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable{ onClick() },
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        textAlign = TextAlign.Center
    )
}