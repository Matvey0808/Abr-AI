package com.example.abr_ai.ui.pages.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
sealed class ContentModel

@Serializable
@SerialName("image")
data class ImageContent(
    @SerialName("image_link") val imageLink: String,
    @SerialName("image_title") val imageTitle: String?
) : ContentModel()

@Serializable
@SerialName("paragraph")
data class TextContent(
    val text: String
) : ContentModel()

@Serializable
@SerialName("header")
data class HeaderContent(
    val text: String
) : ContentModel()

@Serializable
@SerialName("list")
data class ListContent(
    @SerialName("text_list") val textList: List<String>
) : ContentModel()

@Serializable
@SerialName("code")
data class CodeContent(
    val code: String
) : ContentModel()
