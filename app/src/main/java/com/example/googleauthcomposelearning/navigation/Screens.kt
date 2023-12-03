package com.example.googleauthcomposelearning.navigation

sealed class Screens(val route: String) {
    object SignUpScreen: Screens(route = "signup_screen")
    object SignInScreen: Screens(route = "signin_screen")
}