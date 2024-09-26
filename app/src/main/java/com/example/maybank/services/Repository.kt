package com.example.maybank.services

import com.example.maybank.models.Post
import org.koin.dsl.module

val repository = module {
    factory { Repository(get()) }
}

class Repository (private val api: TestApiClient) {
    suspend fun getPosts(): List<Post> {
        return api.getPost()
    }
}