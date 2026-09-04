package com.example.abr_ai.ui.pages.data

import android.text.BoringLayout
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
    private val _isLoading = MutableStateFlow(false)
    val postState: StateFlow<PostState> = _postState.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

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
            }
        }
    }

    fun updatePosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val posts = RetrofitClient.apiService.getPosts()
                _postState.value = PostState.Success(RequestPost(posts.posts))
                _isLoading.value = false
            } catch (e: Exception) {
                _postState.value = PostState.Error("unknown error: $e")
            }
        }
    }
}