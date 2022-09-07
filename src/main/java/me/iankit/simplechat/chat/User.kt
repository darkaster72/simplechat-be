package me.iankit.simplechat.chat

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(private val fullName: String, private val email: String) {
    @Id
    private lateinit var id: String
}