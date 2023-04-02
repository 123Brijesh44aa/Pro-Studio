package com.example.prostudio.pro_studio.domain.repository

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow

interface CustomCameraRepo {

    // we have two suspend function in domain repository
    // first for capturing and saving image and second is for showing camera preview once the user accepts the permission.


    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )

    suspend fun captureAndSaveImage(context: Context): Flow<Uri?>
}