package me.iankit.simplechat.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendMessage implements ChatAction {
    private final ChatActionType actionType = ChatActionType.SEND_MESSAGE;
    private String groupId;
}
