package su.pank.englishapp.model

import kotlinx.serialization.Serializable


@Serializable
data class LessonDTO(
    val id: Int,
    val name: String,
    val language_from: Int,
    val language_to: Int,
    val words: List<Int>
)