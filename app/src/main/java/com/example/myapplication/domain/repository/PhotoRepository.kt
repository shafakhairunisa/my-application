package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.PhotoItem
import com.example.myapplication.domain.model.PhotoDetailItem

interface PhotoRepository {
    suspend fun getPhotoList(page: Int, limit: Int): Result<List<PhotoItem>>
    suspend fun getPhotoDetail(id: String): Result<PhotoDetailItem>
}

