package su.pank.englishapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import su.pank.englishapp.model.Lesson

class LessonsViewModel : ViewModel() {
    var lessons = mutableStateListOf<Lesson>()
}