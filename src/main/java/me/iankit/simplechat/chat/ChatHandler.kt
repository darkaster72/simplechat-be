package me.iankit.simplechat.chat

import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class ChatHandler(
    private val chatDao: ChatDao,
    private val groupDao: GroupDao,
    private val chatMapper: ChatMapper,
) : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        println("Chat connected")
        val output = session.receive()
            .flatMap { msg: WebSocketMessage ->
                try {
                    val action = chatMapper.mapToChatAction(msg)
                    println("New Action :: " + action.actionType)

                    when (action) {
                        is EnterGroupAction -> return@flatMap chatDao.findByGroupId(action.groupId)

                        is SendMessageAction -> return@flatMap chatDao.save(action.toChat())
                            .flatMap { Mono.empty<Chat>() }

                        else -> return@flatMap Mono.empty()
                    }
                } catch (e: JsonProcessingException) {
                    println(e)
                    throw RuntimeException(e)
                } catch (e: Exception) {
                    print(e)
                    throw e
                }
            }
            .map(chatMapper::stringify)
            .doOnError { println(it) }
            .map(session::textMessage)
        return session.send(output)
    }
}