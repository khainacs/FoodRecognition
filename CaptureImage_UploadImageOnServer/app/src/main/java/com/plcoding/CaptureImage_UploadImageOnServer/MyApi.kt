package com.plcoding.CaptureImage_UploadImageOnServer

import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MyApi {

    @Multipart
    @POST("/image")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    )

    companion object {
        val instance by lazy{
            Retrofit.Builder()
                .baseUrl("http://192.168.1.7:9191/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}