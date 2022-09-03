package me.iankit.simplechat.chat;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RequestMapping("chat")
@RestController
public class ChatController {
    private final ChatDao chatDao;

    public ChatController(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @GetMapping(value = "/id/{chatId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessages(@PathVariable Integer chatId) {
        return chatDao.findByChatId(chatId)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping
    public Mono<Chat> newMessage(@RequestBody Chat chat) {
        chat.setCreatedAt(LocalDateTime.now());
        return chatDao.save(chat);
    }
}
