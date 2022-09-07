package me.iankit.simplechat.chat

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
open class WebSocketConfig(private val chatHandler: ChatHandler) {

    @Bean
    open fun handlerMapping(): HandlerMapping {
        val map = mapOf<String, WebSocketHandler>("/ws/chat" to chatHandler)
        return SimpleUrlHandlerMapping(map, -1)
    }

    @Bean
    open fun handlerAdapter(): WebSocketHandlerAdapter {
        return WebSocketHandlerAdapter()
    }
}