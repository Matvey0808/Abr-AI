package com.example.abr_ai.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.abr_ai.ui.pages.data.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.abr_ai.ui.pages.components.PagesBar
import com.example.abr_ai.ui.pages.components.PostCard
import com.example.abr_ai.ui.pages.data.PostState
import com.example.abr_ai.ui.pages.data.network.RequestPost

@ExperimentalMaterial3Api
@Composable
fun HomePage(viewModel: PostViewModel = viewModel()) {
    val statePosts by viewModel.postState.collectAsState()
    val stateIndicator = rememberPullToRefreshState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                title = {
                    Text(
                        "Abr",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (statePosts) {
                is PostState.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                is PostState.Success -> {
                    val posts = (statePosts as PostState.Success).posts
                    PullToRefreshBox(
                        isRefreshing = viewModel.isLoading.collectAsState().value,
                        onRefresh = { viewModel.updatePosts() },
                        state = stateIndicator,
                        indicator = {
                            Indicator(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                state = stateIndicator,
                                isRefreshing = viewModel.isLoading.collectAsState().value,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                        }
                    ) {
                        LazyColumn(Modifier.fillMaxSize()) {
                            items(posts.posts) { post ->
                                PostCard(
                                    post
                                )
                            }
                            item {
                                PagesBar(viewModel)
                            }
                        }
                    }
                }

                is PostState.Error -> {
                    Text((statePosts as PostState.Error).message)
                }
            }
        }
    }
}