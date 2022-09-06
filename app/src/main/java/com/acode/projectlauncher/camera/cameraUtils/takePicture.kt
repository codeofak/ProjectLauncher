package com.acode.composecamera.cameraUtils

import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun ImageCapture.takePicture(executor: Executor): File{
    val photoFile = withContext(Dispatchers.IO){
        kotlin.runCatching {
            File.createTempFile("image","jpg")
        }.getOrElse { ex->
            Log.e("TakePicture","Failed to create temporary file", ex)
            File("/dec/null")
        }
    }
    return suspendCoroutine { continuation ->
        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        takePicture(outputOption,executor,object : ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                continuation.resume(photoFile)
            }

            override fun onError(ex: ImageCaptureException) {
                Log.e("TakePicture", "Image Capture Failed",ex)
            }
        })
    }
}