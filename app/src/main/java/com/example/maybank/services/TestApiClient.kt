package com.example.maybank.services

import com.example.maybank.models.Post
import retrofit2.http.GET

interface TestApiClient {
    @GET("posts")
    suspend fun getPost() : List<Post>
}