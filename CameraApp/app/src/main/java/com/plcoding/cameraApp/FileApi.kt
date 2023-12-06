package com.plcoding.cameraApp

import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Multipart
import retrofit2.http.Part

interface FileApi {
    @Multipart
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    )

    companion object{
        val instance by lazy {
            Retrofit.Builder().baseUrl("https://192.168.1.6:9191/image/")
                .build().create(FileApi::class.java)
        }
    }
}