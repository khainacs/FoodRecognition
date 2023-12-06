package com.plcoding.cameraApp

import android.graphics.Bitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.ByteArrayOutputStream
import java.io.IOException

class FileRepository {

    suspend fun uploadImage(file: Bitmap?): Boolean{
        val byteArrayOutputStream = ByteArrayOutputStream()
        file?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        // Tạo RequestBody từ ByteArray
        val requestBody: RequestBody = byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())

        // Tạo multipart request body
        val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file", "image.jpg", requestBody)
            .build()
        return try{

           FileApi.instance.uploadImage(
               image = MultipartBody.Part.createFormData(
                   "file", "image.jpg", requestBody))
            true
        }catch (e: IOException){
            e.printStackTrace()
            false
        }catch (e: HttpException){
            e.printStackTrace()
            false
        }
    }

}