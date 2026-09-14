package com.example.abr_ai.ui.pages.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.abr_ai.ui.pages.data.PostViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map

@Composable
fun PagesBar(
    viewModel: PostViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .height(48.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.switchUI(arrowOrNull = 2) },
                enabled = if (viewModel.page.collectAsState().value == 1) false else true
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                viewModel.listPages.collectAsState().value.forEach { item ->
                    if (item == -1) {
                        Text("...")
                    } else {
                        ButtonPage({ viewModel.switchUI(item) }, viewModel, item)
                    }
                }
            }
            IconButton(
                onClick = { viewModel.switchUI(arrowOrNull = 1) },
                enabled = if (viewModel.page.collectAsState().value < viewModel.lastPage.collectAsState().value - 1) true else false
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "back"
                )
            }
        }
    }
}

@Composable
fun ButtonPage(
    onClick: () -> Unit,
    viewModel: PostViewModel,
    page: Int
) {
    val number = viewModel.page.collectAsState().value
    OutlinedCard(
        modifier = Modifier
            .size(32.dp)
            .clickable(onClick = onClick, indication = null, interactionSource = null),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (number == page) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("$page")
        }
    }
}