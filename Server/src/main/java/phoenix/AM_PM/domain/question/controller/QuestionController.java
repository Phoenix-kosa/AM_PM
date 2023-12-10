package phoenix.AM_PM.domain.question.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phoenix.AM_PM.domain.answer.service.AnswerService;
import phoenix.AM_PM.domain.question.service.QuestionService;

@Slf4j
@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

}
