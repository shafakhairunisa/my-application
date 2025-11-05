package com.example.myapplication.domain.model

data class PhotoDetailItem(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String
)

