package su.pank.englishapp.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.jsonPrimitive
import su.pank.englishapp.R
import su.pank.englishapp.user

@Composable
fun HelloScreen(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userReady by remember {
            mutableStateOf(
                false
            )
        }
        LaunchedEffect(null) {
            withContext(Dispatchers.IO) {
                delay(1000)
                userReady = true
            }

        }
        SubcomposeAsyncImage(
            model = (user!!.userMetadata!!["avatar_url"]?.jsonPrimitive?.content
                ?: "https://w7.pngwing.com/pngs/981/645/png-transparent-default-profile-united-states-computer-icons-desktop-free-high-quality-person-icon-miscellaneous-silhouette-symbol-thumbnail.png"),
            contentDescription = null
        ) {
            val painterState by remember {
                derivedStateOf {
                    painter.state
                }
            }
            AnimatedContent(
                painterState,
                transitionSpec = {
                    scaleIn(animationSpec = tween(1000)) togetherWith fadeOut(
                        animationSpec = tween(
                            1000
                        )
                    )
                },
                label = ""
            ) { targetState ->
                if (targetState is AsyncImagePainter.State.Success && userReady) Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        this@SubcomposeAsyncImage.painter, null, modifier = Modifier
                            .size(100.dp)
                            .clip(
                                CircleShape
                            )
                    )
                    var nameVis by remember { mutableStateOf(false) }
                    LaunchedEffect(null) {
                        withContext(Dispatchers.IO) {
                            delay(200)
                            nameVis = true
                            delay(5000)
                            withContext(Dispatchers.Main){
                                navController.navigate("mainScreen") {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }

                                }
                            }

                        }
                    }
                    AnimatedVisibility(
                        nameVis, enter = expandHorizontally(
                            animationSpec = tween(2000)
                        ), exit = shrinkHorizontally(animationSpec = tween(2000)),
                        modifier = Modifier.padding(top=10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(stringResource(R.string.welcome_hello_screen) + " ")
                            Text(
                                user!!.userMetadata!!["full_name"]?.jsonPrimitive?.content
                                    ?: "Name",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }


            }
        }


    }
}



