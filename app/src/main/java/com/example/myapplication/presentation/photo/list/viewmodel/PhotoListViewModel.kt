package com.example.myapplication.presentation.photo.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.PhotoItem
import com.example.myapplication.domain.usecase.GetPhotoListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PhotoListUiState(
    val isLoading: Boolean = false,
    val photoList: List<PhotoItem>? = null,
    val error: String? = null
)

class PhotoListViewModel(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoListUiState())
    val uiState: StateFlow<PhotoListUiState> = _uiState.asStateFlow()

    fun loadPhotoList() {
        viewModelScope.launch {
            _uiState.value = PhotoListUiState(isLoading = true)

            getPhotoListUseCase(page = 1, limit = 10)
                .onSuccess { photos ->
                    _uiState.value = PhotoListUiState(photoList = photos)
                }
                .onFailure { exception ->
                    _uiState.value = PhotoListUiState(
                        error = exception.message ?: "An error occurred"
                    )
                }
        }
    }
}

