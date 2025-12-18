package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.PhotoDetailItem
import com.example.myapplication.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(id: String): Result<PhotoDetailItem> {
        return repository.getPhotoDetail(id)
    }
}

