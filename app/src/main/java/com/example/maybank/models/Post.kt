package com.example.maybank.models

import java.io.Serializable

data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Serializable