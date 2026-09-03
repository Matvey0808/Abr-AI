package com.example.abr_ai.ui.pages.data.network

import retrofit2.http.GET

interface ApiService {
    @GET("/posts/?limit=20")
    suspend fun getPosts(): RequestPost
}