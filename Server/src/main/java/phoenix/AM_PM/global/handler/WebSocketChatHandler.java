package phoenix.AM_PM.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import phoenix.AM_PM.domain.chat.dto.ResponseChat;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.ChatRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();
    private final ChatRepository chatRepository;
    private final ObjectMapper objectMapper;

    WebSocketChatHandler(ChatRepository chatRepository, ObjectMapper objectMapper) {
        this.chatRepository = chatRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        list.add(webSocketSession);
        log.info(webSocketSession + " 접속");
    }

    @Override
    @Transactional
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws IOException {
        String payload = textMessage.getPayload();
        log.info("payload: {}", payload);

//        Chat entity = new Chat().builder()
//                .message(payload)
//                .build();
//        Chat chat = chatRepository.save(entity);

//        try {
//            JSONObject json = new JSONObject(payload);
//            textMessage = new TextMessage((CharSequence) json);
//        } catch (JSONException e) {
//            System.out.println(e.getMessage());
//        }

        for(WebSocketSession w : list)
            w.sendMessage(textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        log.info(webSocketSession + " 접속 해제");
        list.remove(webSocketSession);
    }
}
