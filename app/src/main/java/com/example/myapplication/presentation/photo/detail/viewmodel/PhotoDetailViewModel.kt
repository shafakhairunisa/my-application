package com.example.myapplication.presentation.photo.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.PhotoDetailItem
import com.example.myapplication.domain.usecase.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PhotoDetailUiState(
    val isLoading: Boolean = false,
    val photoDetail: PhotoDetailItem? = null,
    val error: String? = null
)

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoDetailUiState())
    val uiState: StateFlow<PhotoDetailUiState> = _uiState.asStateFlow()

    fun loadPhotoDetail(id: String) {
        viewModelScope.launch {
            _uiState.value = PhotoDetailUiState(isLoading = true)

            getPhotoDetailUseCase(id)
                .onSuccess { photoDetail ->
                    _uiState.value = PhotoDetailUiState(photoDetail = photoDetail)
                }
                .onFailure { exception ->
                    _uiState.value = PhotoDetailUiState(
                        error = exception.message ?: "An error occurred"
                    )
                }
        }
    }
}

