package me.iankit.simplechat.chat;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatDao extends ReactiveMongoRepository<Chat, String> {
    @Tailable
    Flux<Chat> findByChatId(Integer chatId);
}
