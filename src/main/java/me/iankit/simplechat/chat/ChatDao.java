package me.iankit.simplechat.chat;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChatDao extends ReactiveMongoRepository<Chat, String> {
    @Tailable
    Flux<Chat> findByGroupId(String groupId);
}
