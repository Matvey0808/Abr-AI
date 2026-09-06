package com.example.abr_ai.ui.pages.data.network

import com.example.abr_ai.ui.pages.data.PostModel

data class RequestPost(
    val posts: List<PostModel>,
    val last: String = "None"
)