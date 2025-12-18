package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.api.PicsumApiService
import com.example.myapplication.domain.model.PhotoItem
import com.example.myapplication.domain.model.PhotoDetailItem
import com.example.myapplication.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: PicsumApiService
) : PhotoRepository {

    override suspend fun getPhotoList(page: Int, limit: Int): Result<List<PhotoItem>> {
        return try {
            val response = apiService.getPhotoList(page, limit)
            val photoItems = response.map { photo ->
                PhotoItem(
                    id = photo.id,
                    author = photo.author,
                    downloadUrl = photo.downloadUrl
                )
            }
            Result.success(photoItems)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPhotoDetail(id: String): Result<PhotoDetailItem> {
        return try {
            val response = apiService.getPhotoDetail(id)
            val photoDetail = PhotoDetailItem(
                id = response.id,
                author = response.author,
                width = response.width,
                height = response.height,
                url = response.url,
                downloadUrl = response.downloadUrl
            )
            Result.success(photoDetail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

