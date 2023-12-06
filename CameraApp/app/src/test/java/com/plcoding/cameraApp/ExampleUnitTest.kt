package com.plcoding.cameraApp

import android.graphics.Bitmap
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    private lateinit var controllerMock: LifecycleCameraController

    @Mock
    private lateinit var viewModel: MainViewModel

    private lateinit var MainActivity: MainActivity

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

    }

    /*
    *
    * */
    @Test
    fun takePhoto_CallsOnPhotoTakenWithImage() {


//        var totalPixel:Int = 0
//        // Verify the logic for each pixel in the bitmap
//        for (x in 0 until rotatedBitmap.width) {
//            for (y in 0 until rotatedBitmap.height) {
//                val pixel = rotatedBitmap.getPixel(x, y)
//                totalPixel += pixel
//                // Your pixel validation logic goes here
//                // For example, check if the pixel value is within a certain range or has specific characteristics
//
//            }
//        }
//        println(totalPixel)
    }

}