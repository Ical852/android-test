package com.example.maybank.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maybank.models.Post
import com.example.maybank.services.Repository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val mainViewModel = module {
    factory { MainViewModel(get()) }
}

class MainViewModel(val repository: Repository): ViewModel() {
    val loading by lazy { MutableLiveData<Boolean>() }
    val data by lazy { MutableLiveData<List<Post>>(arrayListOf()) }
    val failed by lazy { MutableLiveData<Boolean>() }
    val search by lazy { MutableLiveData<String>() }

    init {
        fetch()
    }

    fun fetch() {
        loading.value = true

        viewModelScope.launch {
            try {
                val response = repository.getPosts()
                data.value = response

                loading.value = false
            } catch (e: Exception) {
                loading.value = false
            }
        }
    }
}