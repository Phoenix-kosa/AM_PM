package phoenix.AM_PM.domain.chat.service;

import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.chat.dto.RequestChat;
import phoenix.AM_PM.domain.chat.dto.ResponseChat;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.ChatRepository;
import phoenix.AM_PM.domain.members.repository.MembersRepository;
import phoenix.AM_PM.domain.project.repository.ProjectRepository;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.global.exception.BusinessLogicException;
import phoenix.AM_PM.global.exception.ExceptionCode;

@Service
public class StompChatService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ChatRepository chatRepository;
    private final MembersRepository membersRepository;

    public StompChatService(UserRepository userRepository, ProjectRepository projectRepository, ChatRepository chatRepository, MembersRepository membersRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.chatRepository = chatRepository;
        this.membersRepository = membersRepository;
    }

    public ResponseChat sendChat(RequestChat requestChat) {
        Chat entity = new Chat().builder()
                .message(requestChat.getMessage())
                .user(userRepository.findById(requestChat.getUserId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)))
                .project(projectRepository.findById(requestChat.getProjectId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.PROJECT_NOT_FOUND)))
                .unread(membersRepository.countByProjectId(requestChat.getProjectId()))
                .whoRead("")
                .build();
        Chat chat = chatRepository.save(entity);
        return ResponseChat.from(chat);
    }
}
