package com.example.abr_ai.ui.pages.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abr_ai.ui.pages.data.PostModel

@Composable
fun PostCard(
    model: PostModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = {
                    Log.d("CARD", "CLICK")
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(
            Modifier.fillMaxHeight()
        ) {
            Text(
                model.title,
                modifier = Modifier.padding(6.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (model.complexity != null) {
                    Spacer(modifier = Modifier.width(6.0.dp))
                    Icon(
                        imageVector = Icons.Default.Speed,
                        contentDescription = "views"
                    )
                    Text(
                        model.complexity,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "time",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    model.time,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                Icon(
                    imageVector = Icons.Outlined.Visibility,
                    contentDescription = "time",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    model.views,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
            }
            Text(
                model.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 12.dp),
            )
        }
    }
}