package com.example.ktor_playground.domain

import com.example.ktor_playground.data.photos.GetPhotosRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photosRepo: GetPhotosRepository
) {

    suspend operator fun invoke() = photosRepo.getAllPhotos()
}