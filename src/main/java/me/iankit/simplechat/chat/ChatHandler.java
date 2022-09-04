package me.iankit.simplechat.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ChatHandler implements WebSocketHandler {
    private final ChatDao chatDao;
    private final GroupDao groupDao;

    //    private final Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
//            .zipWith(eventFlux, (time, event) -> event);
    private final ChatMapper chatMapper;

    public ChatHandler(ChatDao chatDao, GroupDao groupDao, ChatMapper chatMapper) {
        this.chatDao = chatDao;
        this.groupDao = groupDao;
        this.chatMapper = chatMapper;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> output = session.receive()
                .flatMap(msg -> {
                    try {
                        var action = chatMapper.mapToChatAction(msg);
                        System.out.println("New Action :: " + action.getActionType());

                        switch (action.getActionType()) {
                            case ENTER_GROUP: {
                                var groupId = ((EnterGroupAction) action).getGroupId();
                                System.out.println("Enter Group " + groupId);
                                return chatDao.findByGroupId(groupId);
                            }
                            case SEND_MESSAGE: {
                                var chat = ((SendMessageAction) action).toChat();
                                return chatDao.save(chat).flatMap((c) -> Mono.empty());
                            }
                        }
                        return Mono.empty();
                    } catch (JsonProcessingException e) {
                        System.out.println(e);
                        throw new RuntimeException(e);
                    }
                })
                .map(chatMapper::stringify)
                .log()
                .map(session::textMessage);

        return session.send(output);
    }
}
