package me.iankit.simplechat.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateGroupDto {
    private String createdBy;
    private String groupName;

    Group toEntity() {
        return new Group(groupName, createdBy);
    }
}
