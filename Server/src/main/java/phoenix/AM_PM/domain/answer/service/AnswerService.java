package phoenix.AM_PM.domain.answer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;

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
    //목록 가져오기
    @Transactional
    public List<Answer> getAnswerList(int id){
//        List<Answer> answer = answerR.findAll();
//        List<AnswerDTO> answerDTO = new ArrayList<>();
//
//        for (Answer entity : answer){
//            AnswerDTO answerdto = AnswerDTO.builder()
//                    .id(entity.getId())
//                    .bulletinId(entity.getBulletinId())
//                    .title(entity.getTitle())
//                    .content(entity.getContent())
//                    .createdDate(entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
//                    .build();
//
//            answerDTO.add(answerdto);
//        }
        return this.answerR.getAnswer(id);
    }
    //게시글 가져오기
    public Answer create(Answer answer, Integer user_idx){
        answer.setUser(userRepository.findById(user_idx).get());
        answer.setCreatedDate(LocalDateTime.now());
        System.out.println(answer);
        return answerR.save(answer);
    }
//    @Transactional
//    public AnswerDTO getAnswer(int id){
//        Answer answer = answerR.findById(id).orElseThrow(() -> new RuntimeException("게시글 없음"));
//        return AnswerDTO.builder()
//                .id(answer.getId())
//                .bulletinId(answer.getBulletinId())
//                .title(answer.getTitle())
//                .content(answer.getContent())
//                .createdDate(answer.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
//                .build();
//    }
// 댓글 등록
//    @Transactional
//    public Answer create(AnswerDTO answerDTO, User user){
//        Answer answer = Answer.from(answerDTO);
//        Answer answer1 = answerR.save(answer);
//        Answer answer2 = Answer.builder()
//                .id(answerDTO.getId())
//                .bulletinId(answerDTO.getBulletinId())
//                .title(answerDTO.getTitle())
//                .content(answerDTO.getContent())
//                .createdDate(LocalDateTime.now())
//                .build();
//        answer.setUser(answerR.findById(userId));
//        answer.setCreatedDate(LocalDateTime.now());
//        System.out.println(answer);
//
//
//        return answerR.save(answer);
//    }
    //댓글 수정
    @Transactional
    public void update(long id, Answer request) {
        Answer answer = answerR.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        answer.update(request.getTitle(), request.getContent());
    }
    //댓글 삭제s
    @Transactional
    public List<Answer> delete(int bulletinId, int id){
        this.answerR.deleteById(bulletinId);
        return this.answerR.getAnswer(id);
    }
}
