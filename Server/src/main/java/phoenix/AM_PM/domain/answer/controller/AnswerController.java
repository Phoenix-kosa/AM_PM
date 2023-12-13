package phoenix.AM_PM.domain.answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.answer.service.AnswerService;

@RestController
@RequestMapping("/api/")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

//    @PostMapping("/answer")
//
//    @PutMapping("/answer/{answer-id}")
//    @DeleteMapping("/answer/{answer-id}")




}
