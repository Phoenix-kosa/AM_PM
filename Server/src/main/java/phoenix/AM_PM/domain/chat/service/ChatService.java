package phoenix.AM_PM.domain.chat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.chat.dto.ResponseChat;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.ChatRepository;
import phoenix.AM_PM.global.pagination.PageResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public PageResponseDto<Chat> readChat(Integer projectId, Pageable pageable, Integer cursorId) {
        Slice<Chat> chatList = chatRepository.chatPage(pageable, projectId, cursorId);
        List<Chat> chats = chatList.stream().collect(Collectors.toList());
        return new PageResponseDto<>(chats, chatList);
    }
}
