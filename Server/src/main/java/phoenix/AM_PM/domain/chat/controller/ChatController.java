package phoenix.AM_PM.domain.chat.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.chat.service.ChatService;
import phoenix.AM_PM.global.config.auth.MyUserDetails;

@RestController
@Log4j2
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 채팅 내역 5개씩
//    @GetMapping("/api/chat/{project-id}")
//    public ResponseEntity readChat(@PathVariable(value = "project-id") Integer projectId,
//                                   @PageableDefault(size = 5) Pageable pageable,
//                                   @RequestParam("cursorId") Integer cursorId,
//                                   @AuthenticationPrincipal MyUserDetails myUserDetails) {
//        return new ResponseEntity(chatService.readChat(projectId, pageable, cursorId, myUserDetails.getUser().getId()), HttpStatus.OK);
//    }

    @GetMapping("/api/read/{chat-id}")
    public ResponseEntity readCheck(@PathVariable("chat-id") Integer chatId,
                                    @AuthenticationPrincipal MyUserDetails myUserDetails) {
        chatService.readCheck(chatId, myUserDetails.getUser().getId());
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
}
