package phoenix.AM_PM.domain.question.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.answer.service.AnswerService;
import phoenix.AM_PM.domain.question.dto.QuestionDTO;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.question.service.QuestionService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("")
    public List<QuestionDTO> questionlist(){
        return questionService.getQuestionList();
    }

    @GetMapping("/{question-id}")
    public QuestionDTO getQuestion(@PathVariable(name = "question-id") int id){
        return questionService.getQuestion(id);
    }

    @PostMapping("")
    public Question create(@RequestBody QuestionDTO questionDTO){
        return questionService.create(questionDTO);
    }

    @PatchMapping("")
    public Question update(@RequestBody QuestionDTO questionDTO){
        return questionService.update(questionDTO);
    }

    @DeleteMapping("/{question-id}")
    public void delete(@PathVariable(name = "question-id") int id){
        questionService.delete(id);
    }

}
