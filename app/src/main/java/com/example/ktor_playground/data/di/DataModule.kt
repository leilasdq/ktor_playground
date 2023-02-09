package com.example.ktor_playground.data.di

import com.example.ktor_playground.data.photos.DefaultGetPhotosRepository
import com.example.ktor_playground.data.photos.GetPhotosRepository
import com.example.ktor_playground.network.photos.GetPhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideGetPhotosRepository(
        service: GetPhotosService
    ): GetPhotosRepository {
        return DefaultGetPhotosRepository(service)
    }
}