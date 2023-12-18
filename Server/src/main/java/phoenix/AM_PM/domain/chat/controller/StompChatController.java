package phoenix.AM_PM.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import phoenix.AM_PM.domain.chat.dto.RequestChat;
import phoenix.AM_PM.domain.chat.dto.ResponseChat;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.ChatRepository;
import phoenix.AM_PM.domain.project.repository.ProjectRepository;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.global.exception.BusinessLogicException;
import phoenix.AM_PM.global.exception.ExceptionCode;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ChatRepository chatRepository;

    @MessageMapping(value = "/chat/message")
    public void message(@RequestBody RequestChat requestChat) {
        Chat entity = new Chat().builder()
                .message(requestChat.getMessage())
                .user(userRepository.findById(requestChat.getUserId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)))
                .project(projectRepository.findById(requestChat.getProjectId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.PROJECT_NOT_FOUND)))
                .unread(0) // 임시
                .whoRead("") // 임시
                .build();
        Chat chat = chatRepository.save(entity);
        template.convertAndSend("/sub/chat/" + requestChat.getProjectId(), ResponseChat.from(chat));
    }
}
