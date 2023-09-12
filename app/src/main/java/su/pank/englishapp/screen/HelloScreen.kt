package su.pank.englishapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import kotlinx.serialization.json.jsonPrimitive
import su.pank.englishapp.user

@Composable
fun HelloScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            rememberAsyncImagePainter(user!!.identities!![0].identityData["avatar_url"]!!.jsonPrimitive.content),
            null
        )

    }
}

