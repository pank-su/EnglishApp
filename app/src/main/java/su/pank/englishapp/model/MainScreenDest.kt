package su.pank.englishapp.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

data class MainScreenDest(
    val name: String,
    val icon: Int,
    val content: @Composable ((navController: NavController) -> Unit)
)
