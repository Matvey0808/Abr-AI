package com.example.abr_ai.ui.pages.data

import com.example.abr_ai.ui.pages.data.network.RequestPost

sealed class PostState {
    object Loading : PostState()

    data class Success(val posts: RequestPost): PostState()
    data class Error(val message: String): PostState()
}