package me.iankit.simplechat.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendMessageAction implements ChatAction {
    private final ChatActionType actionType = ChatActionType.SEND_MESSAGE;
    private String message;
    private String sender;
    private String groupId;

    public Chat toChat() {
        return Chat.builder()
                .message(message)
                .sender(sender)
                .createdAt(LocalDateTime.now())
                .groupId(groupId)
                .build();
    }
}
