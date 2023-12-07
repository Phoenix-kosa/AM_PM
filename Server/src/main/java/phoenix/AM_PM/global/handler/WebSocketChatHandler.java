package phoenix.AM_PM.global.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        list.add(webSocketSession);
        log.info(webSocketSession + " 접속");
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws IOException {
        String payload = textMessage.getPayload();
        log.info("payload: " + payload);

        for(WebSocketSession w : list)
            w.sendMessage(textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        log.info(webSocketSession + " 접속 해제");
        list.remove(webSocketSession);
    }
}
