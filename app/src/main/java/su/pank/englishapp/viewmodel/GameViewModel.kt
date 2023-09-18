package su.pank.englishapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.RealtimeChannel
import io.github.jan.supabase.realtime.broadcastFlow
import io.github.jan.supabase.realtime.createChannel
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalTime
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import su.pank.englishapp.client
import su.pank.englishapp.model.MessageDTO
import su.pank.englishapp.model.RoomDTO
import su.pank.englishapp.user
import java.util.UUID


sealed class GameStatus {
    class NotStarted : GameStatus()

    class InRoom(val roomName: RealtimeChannel) : GameStatus() {

    }

    class InProcess(val userId: UUID) : GameStatus() {
    }

    class Ended : GameStatus()
}


class GameViewModel : ViewModel() {
    private val _gameStatus: MutableStateFlow<GameStatus> =
        MutableStateFlow(GameStatus.NotStarted())
    val gameStatus = _gameStatus.asStateFlow()

    var rooms = flow {
        client.realtime.connect()
        while (true) {
            val rooms = client.postgrest["rooms"].select().decodeList<RoomDTO>()
            emit(rooms)
            for (el in rooms) {
                if (el.user_uid != user!!.id) {
                    val channel = client.realtime.createChannel(el.user_uid)
                    var isOk = false
                    CoroutineScope(Dispatchers.IO).launch {
                        channel.broadcastFlow<MessageDTO>(event = "message").collect {
                            isOk = true
                        }
                        channel.join()
                    }
                    kotlinx.coroutines.delay(5000)
                    channel.leave()
                    if (!isOk) client.postgrest["rooms"].delete {
                        eq("id", el.id)
                    }
                }
            }
            kotlinx.coroutines.delay(10000)
        }
    }


    fun selectRoom(room: RealtimeChannel) {
        CoroutineScope(Dispatchers.Main).launch {

            room.join()
            _gameStatus.emit(GameStatus.InRoom(room))
            while (true) {
                delay(1000)
                room.broadcast(
                    event = "message",
                    JsonObject(mapOf("message" to Json.encodeToJsonElement("hi")))
                )
            }
        }

    }

    fun createRoom(roomName: String = user!!.id) {
        val room = client.realtime.createChannel(roomName)
        CoroutineScope(Dispatchers.Main).launch {
            client.postgrest["rooms"].insert(RoomDTO(10, roomName))
        }
        selectRoom(room)
    }

}