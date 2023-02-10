package com.example.ktor_playground.data.photos

import com.example.ktor_playground.common.ResponseResult
import com.example.ktor_playground.model.Photos
import com.example.ktor_playground.network.photos.GetPhotosService
import io.ktor.client.features.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetPhotosRepository {

    fun getAllPhotos(): Flow<ResponseResult<List<Photos>>>
}

class DefaultGetPhotosRepository @Inject constructor(
    private val service: GetPhotosService
): GetPhotosRepository {

    override fun getAllPhotos(): Flow<ResponseResult<List<Photos>>> = flow<ResponseResult<List<Photos>>> {
        val remoteData = Dispatchers.IO.run {
            service.getAllPhotos()
        }
        emit(ResponseResult.SUCCESS(remoteData.take(100).map { it.toDomain() }))
    }.catch {
        when(it) {
            is RedirectResponseException -> {
                //3xx
                emit(ResponseResult.ERROR(message = Throwable("Further action needs to be taken in order to complete the request")))
            }
            is ClientRequestException -> {
                //4xx
                emit(ResponseResult.ERROR(message = Throwable("Bad syntax or cannot be fulfilled")))
            }
            is ServerResponseException -> {
                //5xx
                emit(ResponseResult.ERROR(message = Throwable("Server error..Try again later")))
            }
            else -> {
                emit(ResponseResult.ERROR(message = Throwable("Unknown error")))
            }
        }
    }.flowOn(Dispatchers.IO)

}