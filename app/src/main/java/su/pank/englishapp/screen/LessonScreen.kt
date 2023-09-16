package su.pank.englishapp.screen

import androidx.compose.runtime.Composable
import su.pank.englishapp.model.Lesson
import su.pank.englishapp.viewmodel.LessonViewModel
import su.pank.englishapp.viewmodel.LessonViewModelFactory

@Composable
fun LessonScreen(
    lesson: Lesson, viewModel: LessonViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        LessonViewModelFactory(lesson = lesson).create(LessonViewModel::class.java)
    }
) {
    println(viewModel.lesson)
}