package phoenix.AM_PM.domain.answer.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;
import phoenix.AM_PM.domain.answer.service.AnswerService;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.global.config.service.JwtServiceImpl;


import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @NonNull
    UserRepository uR;

    private AnswerService answerService;
    private JwtServiceImpl jwtService;

    //가져오기
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getBoardById(@PathVariable int id) {
        Answer answer = answerService.findById(id);
        return ResponseEntity.ok(answer);
    }
    //생성
    @PostMapping("/write")
    public ResponseEntity<String> create(@RequestBody Answer answer, @RequestHeader(required = false, value = "Authorization") String token){
        answerService.create(answer, jwtService.getId(token));
        return ResponseEntity.status(HttpStatus.CREATED).body("Board created successfully");
    }

    //수정
    @PutMapping("/{postNo}/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Answer answer) {
        answerService.update(id, answer);
        return ResponseEntity.ok("Board updated successfully");
    }
    //삭제
    @DeleteMapping("/{postNo}/{id}")
    public void deleteById(int id){
        answerService.delete((int) id);
    }
}
