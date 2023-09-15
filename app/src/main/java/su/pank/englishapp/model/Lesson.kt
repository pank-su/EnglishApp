package su.pank.englishapp.model

import kotlinx.serialization.json.JsonObject

data class Lesson(
    val get_word_and_translate: JsonObject,
    val id: Int,
    val language_from_name: String,
    val language_to_name: String,
    val lesson_name: String
)