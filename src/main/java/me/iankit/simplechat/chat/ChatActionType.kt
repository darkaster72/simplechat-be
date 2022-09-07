package me.iankit.simplechat.chat

enum class ChatActionType(val clazz: Class<out ChatAction>) {
    ENTER_GROUP(EnterGroupAction::class.java),
    CREATE_GROUP(CreateGroupAction::class.java),
    SEND_MESSAGE(SendMessageAction::class.java);
}