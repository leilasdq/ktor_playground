package com.example.ktor_playground.ui.photos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_playground.common.ResponseResult
import com.example.ktor_playground.domain.GetPhotosUseCase
import com.example.ktor_playground.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    init {
        getPhotos()
    }

    private val _photos = MutableStateFlow(emptyList<Photos>())
    val photosData = _photos.asStateFlow()
    private val _photoErrorData = MutableSharedFlow<Throwable>()
    val photoError = _photoErrorData.asSharedFlow()
    private val _photoLoadingData = MutableSharedFlow<Boolean>()
    val photoLoadingData = _photoLoadingData.asSharedFlow()

    private fun getPhotos() {
        viewModelScope.launch {
            getPhotosUseCase.invoke().collect { result ->
                when (result) {
                    is ResponseResult.SUCCESS -> {
                        result.data?.let {
                            _photos.value = it
                        }
                    }
                    is ResponseResult.ERROR -> {
                        result.message.let {
                            _photoErrorData.emit(it)
                        }
                    }
                    is ResponseResult.LOADING ->
                        _photoLoadingData.emit(result.isLoading)
                }
            }

        }
    }

}