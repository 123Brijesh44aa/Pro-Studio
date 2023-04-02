package com.example.prostudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prostudio.pro_studio.presentation.*
import com.example.prostudio.pro_studio.presentation.authentication.AuthenticationViewModel
import com.example.prostudio.pro_studio.presentation.authentication.LoginScreen
import com.example.prostudio.pro_studio.presentation.authentication.SignupScreen
import com.example.prostudio.pro_studio.presentation.camera.CameraViewModel
import com.example.prostudio.pro_studio.presentation.main.FeedsScreen
import com.example.prostudio.pro_studio.presentation.main.ProfileScreen
import com.example.prostudio.pro_studio.presentation.main.SettingScreen
import com.example.prostudio.pro_studio.util.Screens
import com.example.prostudio.ui.theme.ProStudioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProStudioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val authenticationViewModel: AuthenticationViewModel by viewModels()
                    val cameraViewModel: CameraViewModel by viewModels()
                    NavSetUp(navHostController = navController, authenticationViewModel = authenticationViewModel, cameraViewModel = cameraViewModel)
                }
            }
        }
    }

}

@Composable
fun NavSetUp(navHostController: NavHostController, authenticationViewModel: AuthenticationViewModel, cameraViewModel: CameraViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.SplashScreen.route
    ){
        composable(route = Screens.LoginScreen.route){
            LoginScreen(navController = navHostController, authenticationViewModel = authenticationViewModel)
        }
        composable(route = Screens.SignUpScreen.route){
            SignupScreen(navController = navHostController, authenticationViewModel = authenticationViewModel)
        }
        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navHostController, authViewModel = authenticationViewModel)
        }
        composable(route = Screens.GetStartedScreen.route){
            GetStartedScreen()
        }
        composable(route = Screens.PremiumScreen.route){
            PremiumScreen()
        }
        
        // bottom navigation Screens
        composable(route = Screens.FeedScreen.route){
            FeedsScreen(navController = navHostController, cameraViewModel = cameraViewModel)
        }
        composable(route = Screens.SettingScreen.route){
            SettingScreen(navController = navHostController)
        }
        composable(route = Screens.ProfileScreen.route){
            ProfileScreen(navController = navHostController)
        }

        // editor screen where user can edit image
        // PlayGround Route, Notice the "/{id}" in last,
        // its the argument passed down from ShowCameraScreen
        composable(route = Screens.PlayGround.route){
//            val imageUri: Uri = Uri.parse(navBackStack.arguments?.getString("capturedImageUri") ?: "")
            PlayGroundScreen(navController = navHostController, cameraViewModel = cameraViewModel)
        }

        // camera preview screen
        composable(route = Screens.ShowCameraScreen.route){
            ShowCameraScreen(cameraViewModel = cameraViewModel, navController = navHostController)
        }

       
    }

}

