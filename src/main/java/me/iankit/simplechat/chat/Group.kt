package me.iankit.simplechat.chat

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "group")
class Group(val groupName: String, val createdBy: String) {
    @get:Id
    var id: String? = null
        protected set

}