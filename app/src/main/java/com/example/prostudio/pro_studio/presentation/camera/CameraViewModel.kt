package com.example.prostudio.pro_studio.presentation.camera

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prostudio.pro_studio.domain.repository.CustomCameraRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repo: CustomCameraRepo
): ViewModel() {

     private val _imageUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
     val imageUri: StateFlow<Uri?>
        get() = _imageUri

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ){
        viewModelScope.launch {
            repo.showCameraPreview(
                previewView = previewView,
                lifecycleOwner = lifecycleOwner
            )
        }
    }

    fun captureAndSave(context: Context) {
        viewModelScope.launch {
            repo.captureAndSaveImage(context).collect{
                _imageUri.value = it
            }
        }
    }
}