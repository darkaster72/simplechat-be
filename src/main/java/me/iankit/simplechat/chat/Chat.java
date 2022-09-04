package me.iankit.simplechat.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chat")
public class Chat {
    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
    private String groupId;
    private LocalDateTime createdAt;
}