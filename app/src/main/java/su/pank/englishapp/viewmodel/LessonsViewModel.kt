package su.pank.englishapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.pank.englishapp.client
import su.pank.englishapp.model.Lesson

class LessonsViewModel : ViewModel() {
    var lessons = mutableStateListOf<Lesson>()

    init {
        CoroutineScope(Dispatchers.IO).launch{

            lessons = client.postgrest["normal_lessons"].select().decodeList<Lesson>().toMutableStateList()
        }
    }
}