package com.example.abr_ai.ui.pages.data.network

import com.example.abr_ai.ui.pages.data.ContentModel
import com.example.abr_ai.ui.pages.data.PostModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPost(
    val posts: List<PostModel>,
    val last: String = "None"
)

@Serializable
data class RequestContent(
    @SerialName("info_post")
    val content: List<ContentModel>,
)
