package phoenix.AM_PM.domain.answer.service;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerR;

    //목록 가져오기
    public List<AnswerDTO> getAnswerList(){
        List<Answer> answer = answerR.findAll();
        List<AnswerDTO> answerDTO = new ArrayList<>();

        for (Answer entity : answer){
            AnswerDTO answerdto = AnswerDTO.builder()
                    .id(entity.getId())
                    .bulletinId(entity.getBulletinId())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .createdDate(entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            answerDTO.add(answerdto);
        }
        return answerDTO;
    }
    //게시글 가져오기
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
    public Answer create(AnswerDTO answerDTO){
        Answer answer = Answer.builder()
                .id(answerDTO.getId())
                .bulletinId(answerDTO.getBulletinId())
                .title(answerDTO.getTitle())
                .content(answerDTO.getContent())
                .createdDate(LocalDateTime.now())
                .build();
        return answerR.save(answer);
    }
    //게시글 수정
    public Answer update(AnswerDTO answerDTO){
        Answer answer = answerR.findById(answerDTO.getId()).orElseThrow(() -> new RuntimeException("댓글 x"));
        answer.setTitle(answerDTO.getTitle());
        answer.setContent(answerDTO.getContent());
        return answerR.save(answer);
    }
    //게시글 삭제
    public void delete(int id){
        Answer answer = answerR.findById(id).orElseThrow(() -> new RuntimeException("댓글 x"));
        answerR.delete(answer);
    }
}
