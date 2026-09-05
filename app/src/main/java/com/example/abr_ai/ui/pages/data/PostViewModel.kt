package com.example.abr_ai.ui.pages.data

import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abr_ai.ui.pages.data.network.RequestPost
import com.example.abr_ai.ui.pages.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.all
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _postState = MutableStateFlow<PostState>(PostState.Loading)
    private val _isLoading = MutableStateFlow(false)
    private val _page = MutableStateFlow(1)
    private val _lastPage = MutableStateFlow(379 - 1)
    private val _listPages = MutableStateFlow<List<Int>>(emptyList())
    val postState: StateFlow<PostState> = _postState.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    val page: StateFlow<Int> = _page.asStateFlow()
    val listPages: StateFlow<List<Int>> = _listPages.asStateFlow()
    val lastPage: StateFlow<Int> = _lastPage.asStateFlow()

    init {
        fetchPosts()
        switchPages(_page.value)
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

    fun switchPages(number: Int) {
        _page.value = number
        val lastPage = 379

        val start = number - 1
        val end = number + 1

        _listPages.value = if (number != lastPage) {
            when {
                number >= lastPage - 1 -> listOf(1, -1, number - 1, number, number + 1)
                number >= 3 -> listOf(1, -1, start, _page.value, end, -1, lastPage)
                else -> listOf(1, 2, 3, -1, lastPage)
            }
        } else {
            listOf(1, -1, number - 2, number - 1, number)
        }
    }
}