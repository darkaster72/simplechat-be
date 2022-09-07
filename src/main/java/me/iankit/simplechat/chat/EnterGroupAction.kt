package me.iankit.simplechat.chat

class EnterGroupAction(val groupId: String) : ChatAction {
    override val actionType = ChatActionType.ENTER_GROUP
}