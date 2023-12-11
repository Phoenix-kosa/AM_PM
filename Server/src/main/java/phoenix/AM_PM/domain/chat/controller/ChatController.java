package phoenix.AM_PM.domain.chat.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import phoenix.AM_PM.domain.chat.service.ChatService;

@RestController
@Log4j2
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/api/chat/{project-id}")
    public ResponseEntity readChat(@PathVariable(value = "project-id") Integer projectId, @PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity(chatService.readChat(projectId, pageable), HttpStatus.OK);
    }
}
