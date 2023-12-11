package phoenix.AM_PM.domain.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.question.dto.QuestionDTO;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionR;

    //목록 가져오기
    public List<QuestionDTO> getQuestionList(){
        List<Question> question = questionR.findAll();
        List<QuestionDTO> questionDTO = new ArrayList<>();

        for (Question entity : question){
            QuestionDTO questiondto = QuestionDTO.builder()
                    .id(entity.getId())
                    .userId(entity.getUserId())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .createdDate(entity.getCreatedDate())
                    .build();

            questionDTO.add(questiondto);
        }
        return questionDTO;
    }
    //게시글 가져오기
    public QuestionDTO getQuestion(int id){
        Question question = questionR.findById(id).orElseThrow(() -> new RuntimeException("게시글 없음"));
        return QuestionDTO.builder()
                .id(question.getId())
                .userId(question.getUserId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdDate(question.getCreatedDate())
                .build();
    }
    //게시글 등록
    public Question create(QuestionDTO questionDTO){
        Question question = Question.builder()
                .id(questionDTO.getId())
                .userId(questionDTO.getUserId())
                .title(questionDTO.getTitle())
                .content(questionDTO.getContent())
                .createdDate(questionDTO.getCreatedDate())
                .build();
        return questionR.save(question);
    }
    //게시글 수정
    public Question update(QuestionDTO questionDTO){
        Question question = questionR.findById(questionDTO.getId()).orElseThrow(() -> new RuntimeException("게시글 x"));
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());
        return questionR.save(question);
    }
    //게시글 삭제
    public void delete(int id){
        Question question = questionR.findById(id).orElseThrow(() -> new RuntimeException("게시글 x"));
        questionR.delete(question);
    }
}
