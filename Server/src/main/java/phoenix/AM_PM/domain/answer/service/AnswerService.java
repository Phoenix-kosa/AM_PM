package phoenix.AM_PM.domain.answer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.internal.compiler.apt.model.ArrayTypeImpl;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.answer.dto.AddAnswerRequest;
//import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.dto.UpdateAnswerRequest;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;

//import phoenix.AM_PM.domain.member.entity.Roles;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class AnswerService {

    private final AnswerRepository answerR;
//    private final QuestionRepository questionR;
//    private final UserRepository userRepository;

    //저장
    public Answer save(AddAnswerRequest req){
        return answerR.save(req.toEntity());
    }

    //댓글 조회
    public List<Answer> findAll(){
        return answerR.findAll();
    }

    //조회
    public Answer findById(int id) throws IllegalAccessException {
        return answerR.findById(id).orElseThrow(() -> new IllegalAccessException("not found: " + id));
    }

    //삭제
    @Transactional
    public void delete(int id){
        answerR.deleteById(id);
    }

    //수정
    @Transactional
    public Answer update(int id, UpdateAnswerRequest req){
        Answer answer = answerR.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        answer.update(req.getTitle(), req.getContent());
        return answer;
    }
}
