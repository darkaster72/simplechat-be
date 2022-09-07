package me.iankit.simplechat.chat

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "chat")
class Chat(
    val groupId: String,
    val sender: String,
    val receiver: String?,
    val message: String,
    val createdAt: LocalDateTime?,
) {
    @Id
    lateinit var id: String
        protected set
}