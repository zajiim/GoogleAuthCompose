package com.example.googleauthcomposelearning.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googleauthcomposelearning.presentation.signin.SignInScreen
import com.example.googleauthcomposelearning.presentation.signup.SignUpScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Screens.SignUpScreen.route) {
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screens.SignInScreen.route) {
            SignInScreen()
        }
    }
}