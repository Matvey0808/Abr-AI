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
    private val _lastPage = MutableStateFlow(0)
    val postState: StateFlow<PostState> = _postState.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    val page: StateFlow<Int> = _page.asStateFlow()
    val listPages: StateFlow<List<Int>> = _listPages.asStateFlow()
    val lastPage: StateFlow<Int> = _lastPage.asStateFlow()

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

    suspend fun switchPages(number: Int, arrowOrNull: Int = -1) {
        val lastPage = _lastPage.value - 1

        _page.value = when (arrowOrNull) {
            1 -> _page.value + 1
            2 -> _page.value - 1
            else -> number
        }

        val currentPage = _page.value
        val start = currentPage - 1
        val end = currentPage + 1

        _listPages.value = if (currentPage != lastPage) {
            when {
                currentPage >= lastPage - 1 -> listOf(1, -1, start, currentPage, end)
                currentPage >= 3 -> listOf(1, -1, start, currentPage, end, -1, lastPage)
                else -> listOf(1, 2, 3, -1, lastPage)
            }
        } else {
            listOf(1, -1, currentPage - 2, currentPage - 1, currentPage)
        }
        fetchPosts(currentPage)
    }

    fun switchUI(number: Int = 1, arrowOrNull: Int = -1) {
        viewModelScope.launch {
            switchPages(number, arrowOrNull)
        }
    }
}