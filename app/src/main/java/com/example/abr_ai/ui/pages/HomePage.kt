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
import androidx.compose.material3.Switch
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abr_ai.ui.pages.data.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.abr_ai.ui.pages.components.PagesBar
import com.example.abr_ai.ui.pages.components.PostCard
import com.example.abr_ai.ui.pages.data.PostState
import com.example.abr_ai.ui.pages.data.network.RequestPost
import com.example.abr_ai.ui.pages.themes.AbrTheme

@ExperimentalMaterial3Api
@Composable
fun HomePage(viewModel: PostViewModel = viewModel()) {
    AbrTheme(viewModel.isDark.collectAsState().value) {
        val statePosts by viewModel.postState.collectAsState()
        val stateIndicator = rememberPullToRefreshState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Abr",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        Switch(
                            checked = viewModel.isDark.collectAsState().value,
                            { viewModel.switchTheme() },
                            modifier = Modifier.padding(horizontal = 10.dp)
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
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                    }

                    is PostState.Success -> {
                        val posts = (statePosts as PostState.Success).posts
                        PullToRefreshBox(
                            isRefreshing = viewModel.isLoading.collectAsState().value,
                            onRefresh = { viewModel.updatePosts() },
                            state = stateIndicator,
                            indicator = {
                                Indicator(
                                    state = stateIndicator,
                                    isRefreshing = viewModel.isLoading.collectAsState().value,
                                    modifier = Modifier.align(Alignment.TopCenter),
                                    color = MaterialTheme.colorScheme.primary,
                                    containerColor = MaterialTheme.colorScheme.onPrimary
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
}