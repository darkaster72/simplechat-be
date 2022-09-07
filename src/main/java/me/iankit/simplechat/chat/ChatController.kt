package me.iankit.simplechat.chat

import lombok.extern.slf4j.Slf4j
import org.jetbrains.annotations.NotNull
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@RequestMapping("chats")
@RestController
@Slf4j
class ChatController(private val chatDao: ChatDao) {
    @GetMapping(value = ["/{groupId}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getMessages(@PathVariable @NotNull groupId: String): Flux<Chat> {
        return chatDao.findByGroupId(groupId)
            .log()
            .subscribeOn(Schedulers.boundedElastic())
    }

    @PostMapping
    fun newMessage(@RequestBody chat: Chat): Mono<Chat> {
        return chatDao.save(chat)
    }
}