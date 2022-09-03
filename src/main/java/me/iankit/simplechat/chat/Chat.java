package me.iankit.simplechat.chat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "chat")
public class Chat {
    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private Integer groupId;
    private final LocalDateTime createdAt = LocalDateTime.now();
}