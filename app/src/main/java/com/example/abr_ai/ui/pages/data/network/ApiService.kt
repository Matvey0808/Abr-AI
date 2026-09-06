package com.example.abr_ai.ui.pages.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/posts/")
    suspend fun getPosts(
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int
    ): RequestPost
}