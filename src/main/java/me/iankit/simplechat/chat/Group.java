package me.iankit.simplechat.chat;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "group")
public class Group {
    private final String groupName;
    private final String createdBy;
    @Id
    private String id;

    public Group(String groupName, String createdBy) {
        this.groupName = groupName;
        this.createdBy = createdBy;
    }
}
