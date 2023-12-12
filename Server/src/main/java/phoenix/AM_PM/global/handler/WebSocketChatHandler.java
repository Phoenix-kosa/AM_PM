package phoenix.AM_PM.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import phoenix.AM_PM.domain.chat.dto.RequestChat;
import phoenix.AM_PM.domain.chat.dto.ResponseChat;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.ChatRepository;
import phoenix.AM_PM.domain.project.repository.ProjectRepository;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.global.exception.BusinessLogicException;
import phoenix.AM_PM.global.exception.ExceptionCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

    private static Map<String, List<WebSocketSession>> sessionList = new HashMap<>();
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ObjectMapper objectMapper;

    WebSocketChatHandler(ChatRepository chatRepository, UserRepository userRepository, ProjectRepository projectRepository, ObjectMapper objectMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.info(webSocketSession + " 접속");
        String projectId = String.valueOf(webSocketSession.getUri()).split("chat/")[1];
        if(!sessionList.containsKey(projectId))
            sessionList.put(projectId, new ArrayList<>());
        sessionList.get(projectId).add(webSocketSession);

        sessionList.get(projectId).stream().forEach(System.out::println);
    }

    @Override
    @Transactional
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws IOException {
        String payload = textMessage.getPayload();
        log.info("payload: {}", payload);

        RequestChat requestChat = objectMapper.readValue(payload, RequestChat.class);

        Chat entity = new Chat().builder()
                .message(requestChat.getMessage())
                .user(userRepository.findById(requestChat.getUserId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)))
                .project(projectRepository.findById(requestChat.getProjectId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.PROJECT_NOT_FOUND)))
                .unread(0) // 임시
                .whoRead("") // 임시
                .build();
        Chat chat = chatRepository.save(entity);

        String json = objectMapper.writeValueAsString(ResponseChat.from(chat));
        textMessage = new TextMessage(json);

        for(WebSocketSession w : sessionList.get(String.valueOf(requestChat.getProjectId())))
            w.sendMessage(textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        String projectId = String.valueOf(webSocketSession.getUri()).split("chat/")[1];
        log.info(webSocketSession + " 접속 해제");
        sessionList.get(projectId).remove(webSocketSession);
    }
}
