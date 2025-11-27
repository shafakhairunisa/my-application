package com.example.myapplication.presentation.photo.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.GetPhotoDetailUseCase

class PhotoDetailViewModelFactory(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailViewModel::class.java)) {
            return PhotoDetailViewModel(getPhotoDetailUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

