package com.example.prostudio.pro_studio.presentation

import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prostudio.pro_studio.presentation.camera.CameraViewModel
import com.example.prostudio.pro_studio.util.Screens

@Composable
fun TestScreen(cameraViewModel: CameraViewModel, navController: NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(text = "Test Screen", textAlign = TextAlign.Center, color = Color.White, fontSize =  40.sp,
        modifier = Modifier.clickable {
            navController.navigate(Screens.ShowCameraScreen.route){
                popUpTo(Screens.ShowCameraScreen.route){
                    inclusive = true
                }
            }
        }
        )

        val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        backDispatcher?.addCallback {
            navController.navigate(Screens.FeedScreen.route) {
                popUpTo(Screens.FeedScreen.route) {
                    inclusive = false
                }
            }
        }
    }
}