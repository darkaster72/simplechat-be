package me.iankit.simplechat.chat;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequestMapping("chats")
@RestController
public class ChatController {
    private final ChatDao chatDao;

    public ChatController(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @GetMapping(value = "/{groupId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessages(@PathVariable String groupId) {
        return chatDao.findByGroupId(groupId)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping
    public Mono<Chat> newMessage(@RequestBody Chat chat) {
        return chatDao.save(chat);
    }
}
