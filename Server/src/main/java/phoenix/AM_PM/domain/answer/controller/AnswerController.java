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


import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @NonNull
    UserRepository uR;

    @Autowired
    private AnswerService answerService;

//    @GetMapping("")
//    public List<AnswerDTO> answerlist(){
//        return answerService.getAnswerList();
//    }
    //가져오기
    @GetMapping("/{postNo}")
    public ResponseEntity<List<Answer>> read(@PathVariable(name = "postNo") int id){
        return new ResponseEntity<>(this.answerService.getAnswerList(id),HttpStatus.CREATED);
    }
    //생성
//    @PostMapping("/{postNo}")
//    public ResponseEntity<List<Answer>> create(@PathVariable(name = "postNo") int id, @RequestBody AnswerDTO answerDTO, Principal principal, Answer answer){
//
//        answerService.create(answer);
        //        answerDTO.setId(id);
//        answerDTO.setUserId(principal.getName());
//        Optional<User> findNo=uR.findByUserId(principal.getName());
//        findNo.ifPresent(finduserNo->{
//            AnswerDTO.setUserNo(finduserNo.getNickname().getId());
//            Answer answer = this.answerService.create(MapperUtil.convert(answerDTO, Answer.class),finduserNo,answerDTO.getId());
//        });
//        return new ResponseEntity<>(this.answerService.getAnswerList(id), HttpStatus.CREATED);
//        return ResponseEntity.status(HttpStatus.CREATED).body("create successfully");
//    }

//    @PutMapping("/{postNo}/{id}")
//    public ResponseEntity<List<Answer>> update(@PathVariable(name = "postNo") int bulletinId, @PathVariable(name = "id") int id, @RequestBody AnswerDTO answerDTO, Principal principal){
//        answerDTO.setUserId(principal.getName());
//        return new ResponseEntity<>(this.answerService.update(MapperUtil.convert(answerDTO, Answer.class
//        ),id,bulletinId),HttpStatus.CREATED);
//    }
    @DeleteMapping("/{postNo}/{id}")
    public ResponseEntity<List<Answer>> delete(@PathVariable(name = "postNo") int bulletinId, @PathVariable(name = "id") int id, @RequestBody AnswerDTO answerDTO, Principal principal){
        answerDTO.setUserId(principal.getName());
        return new ResponseEntity<>(this.answerService.delete(id,bulletinId),HttpStatus.CREATED);
    }
}
