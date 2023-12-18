package phoenix.AM_PM.domain.answer.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.answer.dto.AddAnswerRequest;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.service.AnswerService;
import phoenix.AM_PM.domain.question.service.QuestionService;


@Slf4j
@RestController //HTTP Response Body에 객체 데이터를 JSON형식으로 반환하는 컨트롤러
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {
//    @NonNull
//    UserRepository uR;

    private AnswerService answerService;
    private QuestionService questionService;

//    private JwtServiceImpl jwtService;

    //가져오기
//    @GetMapping("/{id}")
//    public ResponseEntity<Answer> getBoardById(@PathVariable int id) {
//        Answer answer = answerService.findById(id);
//        return ResponseEntity.ok(answer);
//    }
    //게시물 조회
//    @GetMapping("/{id}")
//    public ResponseEntity getBoardById(@AuthenticationPrincipal MyUserDetails myUserDetails) {
//        List<AnswerDTO> answer = answerService.getProjectList(myUserDetails.getUser().getId());
//        return ResponseEntity.ok(answer);
//    }
    //생성
    @PostMapping("/write")
    public ResponseEntity<Answer> create(@RequestBody AddAnswerRequest req) {
//                                        @AuthenticationPrincipal MyUserDetails myUserDetails)
        Answer answer = answerService.save(req);
        //성공적으로 생성되었으며 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }


    //수정
//    @PutMapping("/{postNo}/{id}")
//    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Answer answer) {
//        answerService.update(id, answer);
//        return ResponseEntity.ok("Board updated successfully");
//    }
    //삭제
//    @DeleteMapping("/{postNo}/{id}")
//    public void deleteById(int id){
//        answerService.delete((int) id);
//    }
}
