package me.iankit.simplechat.chat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "chat")
@Builder
public class Chat {
    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private String groupId;
    private LocalDateTime createdAt;
}