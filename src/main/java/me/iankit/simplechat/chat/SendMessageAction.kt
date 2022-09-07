package me.iankit.simplechat.chat

import java.time.LocalDateTime

data class SendMessageAction(private val groupId: String, private val message: String, private val sender: String) :
    ChatAction {
    override val actionType = ChatActionType.SEND_MESSAGE

    fun toChat(): Chat {
        return Chat(groupId, sender, "", message, LocalDateTime.now())
    }
}