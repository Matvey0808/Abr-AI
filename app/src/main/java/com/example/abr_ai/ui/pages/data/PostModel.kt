package com.example.abr_ai.ui.pages.data

import java.util.Date

data class PostModel(
    val user: String,
    val title: String,
    val complexity: String?,
    val time: String,
    val views: String,
    val description: String,
    val image: String,
    val date: String
)