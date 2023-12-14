package phoenix.AM_PM.domain.answer.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;
import phoenix.AM_PM.domain.answer.service.AnswerService;


import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @NonNull
    AnswerRepository answerR;

    @Autowired
    private AnswerService answerService;

    @GetMapping("")
    public List<AnswerDTO> answerlist(){
        return answerService.getAnswerList();
    }

    @GetMapping("/{answer-id}")
    public AnswerDTO getAnswer(@PathVariable(name = "answer-id") int id){
        return answerService.getAnswer(id);
    }

    @PostMapping("/{}")
    public Answer create(@RequestBody AnswerDTO answerDTO){
        return answerService.create(answerDTO);
    }

    @PatchMapping("")
    public Answer update(@RequestBody AnswerDTO answerDTO){
        return answerService.update(answerDTO);
    }

    @DeleteMapping("/{question-id}")
    public void delete(@PathVariable(name = "question-id") int id){
        answerService.delete(id);
    }

}
