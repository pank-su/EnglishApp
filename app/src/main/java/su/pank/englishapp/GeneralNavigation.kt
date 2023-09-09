package su.pank.englishapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import su.pank.englishapp.screen.Auth

@Composable
fun GeneralNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth"){
        composable("auth"){
            Auth()
        }
    }
}