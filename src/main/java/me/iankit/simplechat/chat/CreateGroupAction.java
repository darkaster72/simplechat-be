package me.iankit.simplechat.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateGroupAction implements ChatAction {
    private final ChatActionType actionType = ChatActionType.ENTER_GROUP;
    private String groupName;
    private String createdBy;
}
