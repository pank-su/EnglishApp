package su.pank.englishapp.viewmodel

import androidx.lifecycle.ViewModel
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import su.pank.englishapp.client
import su.pank.englishapp.model.Lesson

class LessonsViewModel : ViewModel() {
    var lessons: Flow<List<Lesson>> = flow {
        emit(client.postgrest["normal_lessons"].select().decodeList<Lesson>())
    }
}