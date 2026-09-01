package com.example.abr_ai.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.abr_ai.ui.pages.components.PostCard
import com.example.abr_ai.ui.pages.data.PostModel

@Composable
fun HomePage() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn{
                item {
                    PostCard(
                        model = PostModel(
                            title = "Типо название лол",
                            minutes = "10",
                            views = "21",
                            postText = "Привит хабр! сегодня ми будим кушатс вкусниц мадильики от апенаи. Мы ночэнаем!"
                        )
                    )
                }
            }
        }
    }
}