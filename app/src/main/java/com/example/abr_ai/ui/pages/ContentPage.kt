package com.example.abr_ai.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.abr_ai.MainViewModel
import com.example.abr_ai.ui.pages.data.PostViewModel
import com.example.abr_ai.ui.pages.themes.AbrTheme

@ExperimentalMaterial3Api
@Composable
fun ContentPage(globalViewModel: MainViewModel = viewModel(), toBack: () -> Unit) {
    AbrTheme(globalViewModel.isDark.collectAsState().value) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Abr", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    },
                    navigationIcon = {
                        IconButton(toBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "back"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                /// TODO: Write something
            }
        }
    }
}