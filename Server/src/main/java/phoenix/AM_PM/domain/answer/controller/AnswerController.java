package phoenix.AM_PM.domain.answer.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.service.AnswerService;
import phoenix.AM_PM.domain.project.dto.RequestProject;
import phoenix.AM_PM.domain.project.dto.ResponseProject;
import phoenix.AM_PM.domain.question.service.QuestionService;
import phoenix.AM_PM.global.config.auth.MyUserDetails;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/answer")
public class AnswerController {
//    @NonNull
//    UserRepository uR;

    private final AnswerService answerService;
    private QuestionService questionService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
//    private JwtServiceImpl jwtService;

    //가져오기
//    @GetMapping("/{id}")
//    public ResponseEntity<Answer> getBoardById(@PathVariable int id) {
//        Answer answer = answerService.findById(id);
//        return ResponseEntity.ok(answer);
//    }
    //게시물 조회
    @GetMapping("/{id}")
    public ResponseEntity getBoardById(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<AnswerDTO> answer = answerService.getProjectList(myUserDetails.getUser().getId());
        return ResponseEntity.ok(answer);
    }
    //생성
    @PostMapping("/write")
    public ResponseEntity create(@RequestBody AnswerDTO answerDTO,
                                        @AuthenticationPrincipal MyUserDetails myUserDetails){
        ResponseProject project = answerService.create(answerDTO, myUserDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body("Board created successfully");
    }

    //수정
//    @PutMapping("/{postNo}/{id}")
//    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Answer answer) {
//        answerService.update(id, answer);
//        return ResponseEntity.ok("Board updated successfully");
//    }
    //삭제
    @DeleteMapping("/{postNo}/{id}")
    public void deleteById(int id){
        answerService.delete((int) id);
    }
}
