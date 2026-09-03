package com.example.abr_ai.ui.pages.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abr_ai.ui.pages.data.network.RequestPost
import com.example.abr_ai.ui.pages.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _postState = MutableStateFlow<PostState>(PostState.Loading)
    val postState: StateFlow<PostState> = _postState.asStateFlow()

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _postState.value = PostState.Loading
            try {
                val posts = RetrofitClient.apiService.getPosts()
                _postState.value = PostState.Success(RequestPost(posts.posts))
            } catch (e: Exception) {
                _postState.value = PostState.Error("unknown error: $e")
                Log.d("ERROR", "$e")
            }
        }
    }
}