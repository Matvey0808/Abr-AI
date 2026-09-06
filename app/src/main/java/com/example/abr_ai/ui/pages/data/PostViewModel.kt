package com.example.abr_ai.ui.pages.data

import android.text.BoringLayout
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abr_ai.ui.pages.data.network.RequestPost
import com.example.abr_ai.ui.pages.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.all
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {
    private val _postState = MutableStateFlow<PostState>(PostState.Loading)
    private val _isLoading = MutableStateFlow(false)
    private val _page = MutableStateFlow(1)
    private val _listPages = MutableStateFlow<List<Int>>(emptyList())
    private val _lastPage = MutableStateFlow(5)
    val postState: StateFlow<PostState> = _postState.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    val page: StateFlow<Int> = _page.asStateFlow()
    val listPages: StateFlow<List<Int>> = _listPages.asStateFlow()

    init {
        viewModelScope.launch {
            fetchPosts(1)
            switchPages(_page.value)
        }
    }

    suspend fun fetchPosts(page: Int) {
        _postState.value = PostState.Loading
        try {
            val posts = RetrofitClient.apiService.getPosts(page = page)
            _lastPage.value = posts.last.toInt()
            _postState.value = PostState.Success(RequestPost(posts.posts))
        } catch (e: Exception) {
            _postState.value = PostState.Error("unknown error: $e")
        }
    }

    fun updatePosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val posts = RetrofitClient.apiService.getPosts(page = _page.value)
                _postState.value = PostState.Success(RequestPost(posts.posts))
                _isLoading.value = false
            } catch (e: Exception) {
                _postState.value = PostState.Error("unknown error: $e")
            }
        }
    }

    suspend fun switchPages(number: Int) {
        _page.value = number
        val lastPage = _lastPage.value - 1

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
        fetchPosts(number)
    }

    fun switchUI(number: Int) {
        viewModelScope.launch {
            switchPages(number)
        }
    }
}