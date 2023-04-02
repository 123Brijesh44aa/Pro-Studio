package com.example.prostudio.pro_studio.di

import android.app.Application
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.prostudio.pro_studio.data.repository.CustomCameraRepoImpl
import com.example.prostudio.pro_studio.domain.repository.CustomCameraRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CameraModule {

    @Provides
    @Singleton
    fun providesCameraSelector(): CameraSelector{
        // for camera selection front or back
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }
    // we can choose either front camera or back camera
    // all devices has back camera so i selected back

    @Provides
    @Singleton
    fun providesCameraProvider(application: Application): ProcessCameraProvider{
        // for providing camera instance
        return ProcessCameraProvider.getInstance(application).get()
    }

    @Provides
    @Singleton
    fun providesCameraPreview(): Preview{
        // for previewing whatever behind the camera
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun providesImageCapture(): ImageCapture{
        // for capturing image you can select aspect ratio
        // either 16_9 or 4_3
        // we can set flash type if its a back camera some devices has flash for front too.
        return ImageCapture.Builder()
            .setFlashMode(FLASH_MODE_ON)
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
    }

    @Provides
    @Singleton
    fun providesImageAnalysis(): ImageAnalysis{
        // this is for analyzing the image before capturing
        // we will use this more often in machine learning to perform analysis
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    // dependency injection for repository
    @Provides
    @Singleton
    fun providesCustomCameraRepo(
        cameraProvider: ProcessCameraProvider,
        selector: CameraSelector,
        imageCapture: ImageCapture,
        imageAnalysis: ImageAnalysis,
        preview: Preview
    ): CustomCameraRepo {
        return CustomCameraRepoImpl(
            cameraProvider = cameraProvider,
            selector = selector,
            preview = preview,
            imageAnalysis = imageAnalysis,
            imageCapture = imageCapture
        )
    }
}