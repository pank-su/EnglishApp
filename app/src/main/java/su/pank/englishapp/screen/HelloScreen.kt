package su.pank.englishapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import su.pank.englishapp.user

@Composable
fun HelloScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = rememberAsyncImagePainter(user?.identities!![0].identityData["avatar_url"])

        if (painter.state is AsyncImagePainter.State.Error){
            println((painter.state as AsyncImagePainter.State.Error).result.throwable.message)
        }
        Image(painter = painter, contentDescription = "profile_image")
    }
}