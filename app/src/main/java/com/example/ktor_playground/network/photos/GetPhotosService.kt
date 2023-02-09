package com.example.ktor_playground.network.photos

import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

interface GetPhotosService {

    suspend fun getAllPhotos(): List<PhotosResponseDto>
}

class GetPhotosImpl @Inject constructor(
    private val client: HttpClient
) : GetPhotosService {
    override suspend fun getAllPhotos(): List<PhotosResponseDto> =
        try {
            client.get {
                url(HttpsRoute.photosUrl)
            }
        } catch (e: java.lang.Exception) {
            throw e
        }

}