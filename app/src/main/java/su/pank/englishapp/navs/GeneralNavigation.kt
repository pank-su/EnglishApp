package su.pank.englishapp.navs

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import su.pank.englishapp.screen.Auth
import su.pank.englishapp.screen.GameScreen
import su.pank.englishapp.screen.HelloScreen
import su.pank.englishapp.screen.LessonScreen
import su.pank.englishapp.screen.MainScreen

@Composable
fun GeneralNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            Auth(navController)
        }
        composable("helloScreen") {
            HelloScreen(navController)
        }
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable(
            "lesson/{lessonDtoId}",
            arguments = listOf(navArgument("lessonDtoId") { type = NavType.IntType })
        ) {
            val lessonDtoId: Int = it.arguments?.getInt("lessonDtoId") ?: 0
            LessonScreen(lesson = lessonDtoId, navController)
        }
        composable("test"){
            GameScreen()
        }
    }
}