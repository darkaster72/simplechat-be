package me.iankit.simplechat.chat;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GroupDao extends ReactiveMongoRepository<Group, String> {

}
