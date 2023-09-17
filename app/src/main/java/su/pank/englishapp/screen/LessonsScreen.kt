package su.pank.englishapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import su.pank.englishapp.model.Lesson
import su.pank.englishapp.viewmodel.LessonsViewModel


@Composable
fun Lessons(navController: NavController, viewModel: LessonsViewModel = viewModel()) {

    val lessons by viewModel.lessons.collectAsState(initial = listOf())

    if (lessons.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(lessons) { lesson: Lesson ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            navController.navigate("lesson/${lesson.id}")
                        },
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = lesson.lesson_name,
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}