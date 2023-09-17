package su.pank.englishapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import su.pank.englishapp.client
import su.pank.englishapp.model.GetWordAndTranslateDTO
import su.pank.englishapp.model.LessonDTO

class LessonViewModel(val lessonID: Int) : ViewModel() {
    val words: Flow<JsonObject> = flow {
        val request = client.postgrest["wtf_lessons"].select(Columns.ALL) {
            eq("id", lessonID)
        }
        val lessonDTO = request.decodeSingle<LessonDTO>()
        println(lessonDTO)
        val request2 = client.postgrest.rpc(
            "get_word_and_translate",
            GetWordAndTranslateDTO(
                words = lessonDTO.words.map { i -> i.toLong() },
                language_from = lessonDTO.language_from,
                language_to = lessonDTO.language_to
            )
        )
        emit(
            request2.body?.jsonObject ?: JsonObject(mapOf())
        )
    }

    var currentId by mutableStateOf(0);


}

class LessonViewModelFactory(private val lessonID: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = LessonViewModel(lessonID) as T
}