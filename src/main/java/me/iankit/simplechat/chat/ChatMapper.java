package me.iankit.simplechat.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;

@Service
public class ChatMapper {
    private static final ObjectMapper json;

    static {
        JavaTimeModule module = new JavaTimeModule();
        json = new ObjectMapper();
        json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        json.registerModule(module);
    }

    public ChatAction mapToChatAction(WebSocketMessage msg) throws JsonProcessingException {
        var node = json.readTree(msg.getPayloadAsText());
        var type = ChatActionType.valueOf(node.get("type").asText());
        return json.convertValue(node, type.getClazz());
    }

    public String stringify(Chat chat) {
        try {
            return json.writeValueAsString(chat);
        } catch (JsonProcessingException e) {
            var error = json.createObjectNode();
            error.put("error", e.toString());
            System.out.println(e);
            return error.toString();
        }
    }
}
