package com.example.prostudio.pro_studio.data.repository

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.prostudio.pro_studio.domain.repository.CustomCameraRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class CustomCameraRepoImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val selector: CameraSelector,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
    private val imageCapture: ImageCapture
    ): CustomCameraRepo {

    override suspend fun captureAndSaveImage(context: Context) = callbackFlow {

        // for file name
        val name = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.ENGLISH).format(System.currentTimeMillis())
        // for storing
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > 28 ){
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX")
            }
        }

        // for capture output
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()


        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: Uri.EMPTY
                    trySend(savedUri)
                    // here we get the Uri for capture image
                    // we can use this uri to display captured
                    // image in our app but i will not do that
                    // i will just show toast message to keep it simple
                    // you can use coil library to load this uri
                    Toast.makeText(
                        context,
                        "Saved image ${(outputFileResults.savedUri!!)}",
                        Toast.LENGTH_LONG
                    ).show()
                    close()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context,
                        "Some Error occurred ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    close(exception)
                }

            }
        )
        awaitClose{
            cameraProvider.unbindAll()
        }
    }

    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
       preview.setSurfaceProvider(previewView.surfaceProvider)

        try {
            // lets first unbind the camera
            cameraProvider.unbindAll()
            // now lets bind
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis,
                imageCapture
            )
        }
        catch (e: Exception){
            e.printStackTrace()
        }

        // now we will be able to preview so lets implement
        // capture and save
    }
}