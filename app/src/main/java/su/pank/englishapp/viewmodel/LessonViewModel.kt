package su.pank.englishapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject
import su.pank.englishapp.client
import su.pank.englishapp.model.Lesson

class LessonViewModel(val lesson: Lesson) : ViewModel() {
    val words: Flow<JsonObject> = flow {
        client.postgrest.rpc("", object {

        })
    }

}

class LessonViewModelFactory(private val lesson: Lesson) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = LessonViewModel(lesson) as T
}