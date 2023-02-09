package com.example.ktor_playground.network.di

import com.example.ktor_playground.network.photos.GetPhotosImpl
import com.example.ktor_playground.network.photos.GetPhotosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            Logging {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
                this.acceptContentTypes = listOf(ContentType.Application.Json)
            }
        }
    }

    @Provides
    fun provideGetPhotosService(client: HttpClient): GetPhotosService {
        return GetPhotosImpl(client)
    }
}