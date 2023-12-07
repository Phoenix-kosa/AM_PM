package phoenix.AM_PM.domain.chat.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ChatController {
    @GetMapping("/api/chat/{project-id}")
    public void readChat(@PathVariable(value = "project-id") Integer projectId) {
        log.info("chat get: " + projectId);
    }
}
