package su.pank.englishapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import su.pank.englishapp.navs.MainScreenNavigation


@Composable
fun MainScreen(navController: NavHostController) {
    MainScreenNavigation(navController)
}