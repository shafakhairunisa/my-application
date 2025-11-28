package com.example.myapplication.presentation.photo.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.GetPhotoListUseCase

class PhotoListViewModelFactory(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoListViewModel::class.java)) {
            return PhotoListViewModel(getPhotoListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

