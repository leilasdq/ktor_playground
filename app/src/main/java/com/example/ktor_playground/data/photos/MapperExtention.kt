package com.example.ktor_playground.data.photos

import com.example.ktor_playground.model.Photos
import com.example.ktor_playground.network.photos.PhotosResponseDto

fun PhotosResponseDto.toDomain() =
    Photos(id, title, url, thumbnailUrl)