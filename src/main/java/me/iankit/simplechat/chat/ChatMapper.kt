package me.iankit.simplechat.chat

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.stereotype.Service
import org.springframework.web.reactive.socket.WebSocketMessage

@Service
class ChatMapper {
    private val json: ObjectMapper

    init {
        val module = JavaTimeModule()
        json = ObjectMapper()
        json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        json.registerModule(module)
        json.registerKotlinModule()
    }

    @Throws(JsonProcessingException::class)
    fun mapToChatAction(msg: WebSocketMessage): ChatAction {
        val node = json.readTree(msg.payloadAsText)
        val type = ChatActionType.valueOf(node["type"].asText())
        return json.convertValue(node, type.clazz)
    }

    fun stringify(chat: Chat): String {
        return try {
            json.writeValueAsString(chat)
        } catch (e: JsonProcessingException) {
            val error = json.createObjectNode()
            error.put("error", e.toString())
            println(e)
            error.toString()
        }
    }
}