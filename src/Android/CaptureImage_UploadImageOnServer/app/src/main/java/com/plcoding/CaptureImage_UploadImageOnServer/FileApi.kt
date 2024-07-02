package com.plcoding.CaptureImage_UploadImageOnServer

import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileApi{
    @Multipart
    @POST("/image")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    )

    companion object {
            val instance by lazy {
                Retrofit.Builder()
                    .baseUrl("http://172.20.10.8:9191/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FileApi::class.java)

        }
    }
}