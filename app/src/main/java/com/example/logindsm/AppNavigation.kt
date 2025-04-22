package com.example.logindsm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.logindsm.screen.AuthScreen
import com.example.logindsm.screen.LoginScreen
import com.example.logindsm.screen.WelcomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost( navController= navController, startDestination = "auth"){

        composable("auth"){
            AuthScreen(modifier, navController)
        }
        composable("login"){
            LoginScreen(modifier, navController)
        }

        composable(
            route = "welcome/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            WelcomeScreen(modifier, email, navController)
        }
    }
}