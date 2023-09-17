package su.pank.englishapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import su.pank.englishapp.R
import su.pank.englishapp.viewmodel.LessonViewModel
import su.pank.englishapp.viewmodel.LessonViewModelFactory

@Composable
fun LessonScreen(
    lesson: Int, navController: NavController, viewModel: LessonViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        LessonViewModelFactory(lesson).create(LessonViewModel::class.java)
    }
) {
    val words by viewModel.words.collectAsState(initial = JsonObject(mapOf()))
    var cardWatched by remember {
        mutableStateOf(false)
    }
    if (words.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val spoilerText by remember {
                derivedStateOf {
                    if (cardWatched) (words[words.keys.toList()[viewModel.currentId]]?.jsonPrimitive?.content
                        ?: "") else "";
                }
            }
            val buttonTextResource by remember {
                derivedStateOf {
                    if (!cardWatched) R.string.watch_card else (if (words.size - 1 != viewModel.currentId) R.string.next_card else R.string.cards_out)

                }
            }

            Text(text = words.keys.toList()[viewModel.currentId])
            Text(text = spoilerText)
            Button(onClick = {
                if (!cardWatched) cardWatched = true
                else if (words.size - 1 != viewModel.currentId){
                    cardWatched = false
                    viewModel.currentId++;
                }
                else navController.popBackStack()

            }) {
                Text(
                    text = stringResource(id = buttonTextResource)
                )
            }
        }
    }
}