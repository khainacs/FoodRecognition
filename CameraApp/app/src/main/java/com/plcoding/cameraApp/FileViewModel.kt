package com.plcoding.cameraApp

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FileViewModel(
    private val  repository: FileRepository = FileRepository()
): ViewModel() {
    fun uploadImage(file: Bitmap?){
        viewModelScope.launch {
            repository.uploadImage(file)
        }
    }
}