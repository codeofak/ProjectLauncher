package com.acode.composecamera.composables

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview

import androidx.camera.core.UseCase
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.acode.composecamera.cameraUtils.executor
import com.acode.composecamera.cameraUtils.getCameraProvider
import com.acode.composecamera.cameraUtils.takePicture
import com.acode.composecamera.permission.Permission
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    onImageFile:(File) -> Unit = {}
){
    val context = LocalContext.current
    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "You said you wanted a picture, so I'm going to have to ask for permission.",
        permissionNotAvailableContent = {
            Column(modifier) {
                Text(text = "No Camera!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    context.startActivity((Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }))
                }) {
                    Text(text = "Open Setting")
                }
            }
        }
    ){
        //Text(text = "It Worked")
        //CameraCapture()
        Box(modifier = modifier){
            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            val lifecycleOwner = LocalLifecycleOwner.current
            var previewUseCase by remember {
                mutableStateOf<UseCase>(Preview.Builder().build())
            }
            val imageCaptureUseCase by remember {
                mutableStateOf(
                    ImageCapture.Builder()
                        .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
                        .build()
                )
            }

            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                onUseCase = {previewUseCase = it}
            )
//            Button(
//                modifier = Modifier
//                    .wrapContentSize()
//                    .padding(16.dp)
//                    .align(Alignment.BottomCenter),
//                onClick = {
//                    coroutineScope.launch {
//                        imageCaptureUseCase.takePicture(context.executor).let {
//                            onImageFile(it)
//                        }
//                    }
//                }) {
//                Text(text = "Click")
//
//            }
            LaunchedEffect(previewUseCase ){
                val cameraProvider = context.getCameraProvider()
                try {
                    //must unbind the use-cases before rebinding them
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,cameraSelector,previewUseCase,imageCaptureUseCase
                    )
                }catch (ex: Exception){
                    Log.e("CameraCapture", "Failed to bind camera cases",ex)
                }
            }

        }
    }
}