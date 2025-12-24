package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.PhotoItem
import com.example.myapplication.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotoListUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(page: Int, limit: Int): Result<List<PhotoItem>> {
        return repository.getPhotoList(page, limit)
    }
}

