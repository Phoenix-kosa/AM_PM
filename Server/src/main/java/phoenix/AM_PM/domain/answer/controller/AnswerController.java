package phoenix.AM_PM.domain.answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.AM_PM.domain.answer.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;


}
