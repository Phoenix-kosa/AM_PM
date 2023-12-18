package phoenix.AM_PM.domain.answer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.answer.dto.AnswerCreateRequestDTO;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;

import phoenix.AM_PM.domain.member.entity.Roles;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.domain.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerR;
    private final QuestionRepository questionR;
    private final UserRepository userRepository;

//    // 목록 가져오기
//    @Transactional
//    public List<Answer> getAnswerList(int id){
//        return this.answerR.getAnswer(id);
//    }

    //댓글 작성
    public Answer create(AnswerDTO answerDTO, User user){
        Answer entity = Answer.from(answerDTO);
        Answer answer = answerR.save(entity);
        Answer member = new Members().builder()
                .user(user)
                .project(project)
                .roles(Roles.representative_member)
                .build();
        memberRepository.save(member);

        return ResponseProject.of(project);
    }

    public Answer findById(int id) {
        return answerR.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
    //댓글 수정
//    @Transactional
//    public void update(long id, Answer request) {
//        Answer answer = answerR.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
//        answer.update(request.getTitle(), request.getContent());
//    }
    //댓글 삭제
    public void delete(int id){
        answerR.deleteById(id);
    }
}
