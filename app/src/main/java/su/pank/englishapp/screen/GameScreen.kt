package su.pank.englishapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import su.pank.englishapp.ui.theme.EnglishAppTheme
import su.pank.englishapp.viewmodel.GameStatus
import su.pank.englishapp.viewmodel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {

    val status by viewModel.gameStatus.collectAsState()
    when (status){
        is GameStatus.NotStarted -> {
            val rooms = viewModel.rooms.collectAsState(initial = setOf())
            LazyColumn {
                items(rooms.value.toList()) { room ->
                    Button(onClick = {}) {
                        Text(room.user_uid)
                    }
                }
                item {
                    Button(onClick = {
                        viewModel.createRoom()
                    }) {
                        Text("Add room")
                    }
                }
            }
        }
        is GameStatus.InRoom -> Text("Вы в комнате, ожидайте подключения")
        else -> Box{}
    }


}

@Preview
@Composable
fun GamePreview() {
    EnglishAppTheme {
        GameScreen()
    }
}