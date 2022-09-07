package me.iankit.simplechat.chat

data class CreateGroupAction(private val groupName: String, private val createdBy: String) : ChatAction {
    override val actionType = ChatActionType.ENTER_GROUP
}