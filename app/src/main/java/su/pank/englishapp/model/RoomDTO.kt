package su.pank.englishapp.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomDTO(val id: Int, val user_uid: String)
