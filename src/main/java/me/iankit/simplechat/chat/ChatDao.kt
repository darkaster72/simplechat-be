package me.iankit.simplechat.chat

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Tailable
import reactor.core.publisher.Flux

interface ChatDao : ReactiveMongoRepository<Chat, String> {
    @Tailable
    fun findByGroupId(groupId: String): Flux<Chat>
}