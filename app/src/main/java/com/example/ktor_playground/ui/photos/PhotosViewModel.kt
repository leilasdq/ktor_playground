package com.example.ktor_playground.ui.photos

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

    private val _photos: MutableStateFlow<List<Photos>> = MutableStateFlow(listOf())
    val photosData = _photos

    private fun getPhotos() {
        viewModelScope.launch {
            getPhotosUseCase.invoke().collect { result ->
                when (result) {
                    is ResponseResult.SUCCESS -> {
                        result.data?.let {
                            _photos.value = it
                        }
                    }
                    else -> {

                    }
                }

            }
        }

    }
}