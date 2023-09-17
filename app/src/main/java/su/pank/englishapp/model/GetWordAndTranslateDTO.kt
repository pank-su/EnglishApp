package su.pank.englishapp.model

import kotlinx.serialization.Serializable


@Serializable
data class GetWordAndTranslateDTO(
    val words: List<Long>,
    val language_from: Int,
    val language_to: Int
)
