package com.example.prostudio.pro_studio.util

sealed class Screens(val route: String) {

    object SplashScreen: Screens("splash_screen")
    object LoginScreen: Screens("login_screen")
    object SignUpScreen: Screens("signup_screen")
    object FeedScreen: Screens("feed_screen")
    object ProfileScreen: Screens("profile_screen")
    object SettingScreen: Screens("setting_screen")
    object GetStartedScreen: Screens("getStarted_screen")
    object PremiumScreen: Screens("premium_screen")
    object PlayGround: Screens("playGround_screen")
    object ShowCameraScreen: Screens("showCamera_screen")

}