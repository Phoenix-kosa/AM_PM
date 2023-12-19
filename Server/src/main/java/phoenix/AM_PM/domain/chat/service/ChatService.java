package phoenix.AM_PM.domain.chat.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.ChatRepository;
import phoenix.AM_PM.domain.members.repository.MembersRepository;
import phoenix.AM_PM.global.config.auth.MyUserDetails;
import phoenix.AM_PM.global.exception.BusinessLogicException;
import phoenix.AM_PM.global.exception.ExceptionCode;
import phoenix.AM_PM.global.pagination.PageResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final MembersRepository membersRepository;

    ChatService(ChatRepository chatRepository, MembersRepository membersRepository) {
        this.chatRepository = chatRepository;
        this.membersRepository = membersRepository;
    }

    @Transactional
    public PageResponseDto<Chat> readChat(Integer projectId, Pageable pageable, Integer cursorId, Integer userId) {
        Slice<Chat> chatList = chatRepository.chatPage(pageable, projectId, cursorId);
        chatList.stream().forEach(chat -> readCheck(chat.getId(), userId));
        List<Chat> chats = chatList.stream().collect(Collectors.toList());
        return new PageResponseDto<>(chats, chatList);
    }

    @Transactional
    public void readCheck(Integer chatId, Integer userId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.CHAT_NOT_FOUND));
        int length = 1;
        if(!chat.getWhoRead().isEmpty()) {
            String[] reads = chat.getWhoRead().split(",");
            length = reads.length;
            if(!Arrays.asList(reads).contains(String.valueOf(userId))) {
                chat.updateWhoRead(chat.getWhoRead() + "," + userId);
                length++;
            }
        }
        else {
            chat.updateWhoRead(String.valueOf(userId));
        }
        chat.updateUnread(membersRepository.countByProjectId(chat.getProject().getId()) - length);
    }
}
