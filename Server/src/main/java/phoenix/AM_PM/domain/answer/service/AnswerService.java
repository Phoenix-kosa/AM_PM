package phoenix.AM_PM.domain.answer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.answer.dto.AddAnswerRequest;
//import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;

//import phoenix.AM_PM.domain.member.entity.Roles;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;

@Slf4j
@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class AnswerService {

    private final AnswerRepository answerR;
//    private final QuestionRepository questionR;
//    private final UserRepository userRepository;

    public Answer save(AddAnswerRequest req){
        return answerR.save(req.toEntity());
    }
//    // 목록 가져오기
//    @Transactional
//    public List<Answer> getAnswerList(int id){
//        return this.answerR.getAnswer(id);
//    }

    //댓글 작성
//    public Answer create(AnswerDTO answerDTO, User user){
//        Answer entity = Answer.from(answerDTO);
//        Answer answer = answerR.save(entity);
//        Answer member = new Members().builder()
//                .user(user)
//                .project(project)
//                .roles(Roles.representative_member)
//                .build();
//        memberRepository.save(member);
//
//        return ResponseProject.of(project);
//    }

//    public Answer findById(int id) {
//        return answerR.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
//    }
    //댓글 수정
//    @Transactional
//    public void update(long id, Answer request) {
//        Answer answer = answerR.findById((int) id)
//                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
//        answer.update(request.getTitle(), request.getContent());
//    }
    //댓글 삭제
//    public void delete(int id){
//        answerR.deleteById(id);
//    }
}
