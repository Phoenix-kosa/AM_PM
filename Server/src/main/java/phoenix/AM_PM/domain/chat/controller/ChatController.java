package phoenix.AM_PM.domain.chat.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import phoenix.AM_PM.domain.chat.service.ChatService;

@RestController
@Log4j2
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 채팅 내역 5개씩
    @GetMapping("/api/chat/{project-id}")
        public ResponseEntity readChat(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable(value = "project-id") Integer projectId, @PageableDefault(size = 5) Pageable pageable, @RequestParam("cursorId") Integer cursorId) {
            return new ResponseEntity(chatService.readChat(projectId, pageable, cursorId), HttpStatus.OK);
    }
}
