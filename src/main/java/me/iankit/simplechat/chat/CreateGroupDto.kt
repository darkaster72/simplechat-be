package me.iankit.simplechat.chat

data class CreateGroupDto(private val createdBy: String, private val groupName: String) {

    fun toEntity(): Group {
        return Group(groupName, createdBy)
    }
}