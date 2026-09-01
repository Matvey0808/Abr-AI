package com.example.abr_ai.ui.pages.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = {
                    Log.d("CARD", "CLICK")
                }
            )
    ) {
        Column {
            Text(
                "Название поста",
                modifier = Modifier.padding(6.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "10 мин 10 просмотров",
                modifier = Modifier.padding(horizontal = 6.dp)
            )
            Text(
                "СтатьиТекстСтатьиТекстСтатьиТекстСтатьиТекстСтатьиТекстСтатьdasdaasddddddddddasajbkfsadfjsafsdafshadfиТекстСтатьиТекстСтатьиТекстСтатьиТекстСтатьиТекстСтать",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 12.dp),
            )
        }
    }
}