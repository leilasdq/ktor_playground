package com.example.ktor_playground.network.photos

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PhotosResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String,
)