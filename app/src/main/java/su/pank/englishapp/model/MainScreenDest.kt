package su.pank.englishapp.model

import androidx.compose.runtime.Composable

data class MainScreenDest(val name: String, val icon: Int, val content: @Composable (() -> Unit))
