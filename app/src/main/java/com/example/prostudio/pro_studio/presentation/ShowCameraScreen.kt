package com.example.prostudio.pro_studio.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import com.example.prostudio.R
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.prostudio.pro_studio.presentation.camera.CameraViewModel
import com.example.prostudio.pro_studio.util.Screens

@Composable
fun ShowCameraScreen(cameraViewModel: CameraViewModel, navController: NavController) {
    val imageUri by cameraViewModel.imageUri.collectAsState(null)

    var cameraPermissionState by remember { mutableStateOf(false) }

    val requestCameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        cameraPermissionState = isGranted
    }


    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var previewView: PreviewView

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // we will show camera preview once permissions are granted
        if (cameraPermissionState ) {
            Box(
                modifier = Modifier
                    .height(screenHeight * 0.85f)
                    .width(screenWidth)
            ) {
                AndroidView(
                    factory = {
                        previewView = PreviewView(it)
                        cameraViewModel.showCameraPreview(
                            previewView = previewView,
                            lifecycleOwner = lifecycleOwner
                        )
                        previewView
                    },
                    modifier = Modifier.height(screenHeight * 0.85f)
                )

//                if (imageUri != null) {
//                    navController.navigate(Screens.TestScreen.route) {
//                        popUpTo(Screens.TestScreen.route) {
//                            inclusive = true
//                        }
//                    }
//                }
            }
        } else {
            SideEffect {
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        Box(
            modifier = Modifier
                .height(screenHeight * 0.15f),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                if (cameraPermissionState) {
                    cameraViewModel.captureAndSave(context)

                } else {
                    requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    android.widget.Toast.makeText(
                        context, "Please Accept permission in app setting",
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp),
                    tint = Color.DarkGray
                )
            }

                if (imageUri != null){
                    navController.navigate(Screens.PlayGround.route){
                        popUpTo(Screens.PlayGround.route){
                            inclusive = true
                        }
                    }
                }
        }
    }
}
