package me.iankit.simplechat.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static java.time.LocalTime.now;
import static java.util.UUID.randomUUID;

@Component
public class EchoHandler implements WebSocketHandler {

    private static final ObjectMapper json = new ObjectMapper();
    private final Flux<String> eventFlux = Flux.generate(sink -> {
        Event event = new Event(randomUUID().toString(), now().toString());
        try {
            sink.next(json.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            sink.error(e);
        }
    });
    private final Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
            .zipWith(eventFlux, (time, event) -> event);

    @Override
    public Mono<Void> handle(WebSocketSession session) {
//        return session.send(intervalFlux
//                        .map(session::textMessage))
//                .and(session.receive()
//                        .map(WebSocketMessage::getPayloadAsText)
//                        .log());
        return session
                .send(session.receive()
                        .map(msg -> "RECEIVED ON SERVER :: " + msg.getPayloadAsText())
                        .log()
                        .map(session::textMessage)
                );
    }
}
