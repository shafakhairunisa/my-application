package com.example.myapplication.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.PhotoItem
import com.example.myapplication.domain.usecase.GetPhotoListUseCase
import kotlinx.coroutines.launch

class PhotoListViewModel(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : ViewModel() {

    private val _photoList = MutableLiveData<List<PhotoItem>>()
    val photoList: LiveData<List<PhotoItem>> = _photoList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadPhotoList() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            getPhotoListUseCase(page = 1, limit = 10)
                .onSuccess { photos ->
                    _photoList.value = photos
                    _isLoading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "An error occurred"
                    _isLoading.value = false
                }
        }
    }
}

