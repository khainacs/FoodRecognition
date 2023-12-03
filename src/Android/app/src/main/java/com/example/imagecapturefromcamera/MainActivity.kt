package com.example.imagecapturefromcamera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            val file = context.createImageFile()
            val uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                context.packageName + ".provider", file
            )

            var capturedImageUri by remember {
                mutableStateOf<Uri>(Uri.EMPTY)
            }
            val viewModel = viewModel<FileViewModel>()

            val cameraLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
                    capturedImageUri = uri
                }


            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ){
                if (it)
                {
                    Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                    cameraLauncher.launch(uri)
                }
                else
                {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }


            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {

                Button(onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
                    {
                        cameraLauncher.launch(uri)
                    }
                    else
                    {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }) {
                    Text(text = "Capture Image")
                }

                Button(onClick = {
                    val image = File(cacheDir, "myImage.jpg")
                    image.createNewFile()
                    image.outputStream().use{
                        assets.open("pho.jpg").copyTo(it)
                    }
                    viewModel.uploadImage(image)
                }) {
                    Text(text = "Upload Image")
                }

            }


            if (capturedImageUri.path?.isNotEmpty() == true)
            {
                println("th uri is: $capturedImageUri")
                Image(
                    modifier = Modifier
                        .padding(16.dp, 8.dp),
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = null
                )
            }
            else
            {
                Image(
                    modifier = Modifier
                        .padding(16.dp, 8.dp),
                    painter = painterResource(id = R.drawable.ic_image),
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun imageCaptureFromCamera()
{




}


fun Context.createImageFile(): File {

    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
    val imageFileName = "JPEG_$timeStamp";
    val image = File.createTempFile(
        imageFileName,
        ".jpeg",
        externalCacheDir
    )

    return image
}

fun uploadImage(image: File) {
    val client = OkHttpClient()

    val mediaType = "image/jpeg".toMediaTypeOrNull()

    // Tạo RequestBody từ file ảnh
    val requestBody: RequestBody = image.asRequestBody(mediaType)

    // Tạo multipart request body
    val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", image.name, requestBody)
        .build()

     val request = Request.Builder().url("http://192.168.1.6:9191/image").post(multipartBody).build()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            // Xử lý phản hồi từ server khi upload thành công
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Log.d("UploadImage", "Upload successful. Response: $responseBody")
                // Xử lý responseBody nếu cần
            } else {
                Log.e("UploadImage", "Upload failed. Response code: ${response.code}")
                // Xử lý lỗi khi nhận phản hồi từ server
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            // Xử lý lỗi khi gửi request
            e.printStackTrace()
            Log.e("UploadImage", "Upload failed:    ${e.message}}")
        }
    })
}
