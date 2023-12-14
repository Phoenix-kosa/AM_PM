package phoenix.AM_PM.domain.answer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;
import phoenix.AM_PM.domain.answer.dto.AnswerDTO;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.service.UserService;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerR;
    private final QuestionRepository questionR;
    private final UserService userR;
    //목록 가져오기
    @Transactional
    public List<Answer> getAnswerList(int bulletinId){
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
        return this.answerR.getAnswer(bulletinId);
    }
    //게시글 가져오기
    @Transactional
    public AnswerDTO getAnswer(int id){
        Answer answer = answerR.findById(id).orElseThrow(() -> new RuntimeException("게시글 없음"));
        return AnswerDTO.builder()
                .id(answer.getId())
                .bulletinId(answer.getBulletinId())
                .title(answer.getTitle())
                .content(answer.getContent())
                .createdDate(answer.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }
    //게시글 등록
    @Transactional
    public Answer create(Answer answer, User user, int bulletinId){
//        Answer answer = Answer.builder()
//                .id(answerDTO.getId())
//                .bulletinId(answerDTO.getBulletinId())
//                .title(answerDTO.getTitle())
//                .content(answerDTO.getContent())
//                .createdDate(LocalDateTime.now())
//                .build();
        Optional<Question> question = this.questionR.findById(bulletinId);
        question.ifPresent(re->{
            answer.changePost(re);
        });
        answer.changeUserId(user);
        return this.answerR.save(answer);
    }
    //게시글 수정
    @Transactional
    public List<Answer> update(Answer answer, int bulletinId1, int bulletinId2){
//        Answer answer = answerR.findById(answerDTO.getId()).orElseThrow(() -> new RuntimeException("댓글 x"));
//        answer.setTitle(answerDTO.getTitle());
//        answer.setContent(answerDTO.getContent());
        Optional<Answer> update=this.answerR.findById(bulletinId1);
        update.ifPresent(origin->{
            origin.setContent(answer.getContent());
            this.answerR.save(origin);
        });
        return this.answerR.getAnswer(bulletinId2);
    }
    //게시글 삭제
    public void delete(int id){
        Answer answer = answerR.findById(id).orElseThrow(() -> new RuntimeException("댓글 x"));
        answerR.delete(answer);
    }
}
