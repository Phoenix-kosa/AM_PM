package phoenix.AM_PM.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.question.dto.QuestionDTO;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionR;

    @BeforeEach
    public void setup() {
        System.out.println("=".repeat(80));
    }

    @Order(1)
    @Test
    public void testFindAll() {
        List<Question> answer = questionR.findAll();
        answer.forEach(System.out::println);
    }

    @Order(2)
    @Test
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
//    //게시글 등록
//    public Question create(QuestionDTO questionDTO){
//        Question question = Question.builder()
//                .id(questionDTO.getId())
//                .userId(questionDTO.getUserId())
//                .title(questionDTO.getTitle())
//                .content(questionDTO.getContent())
//                .createdDate(questionDTO.getCreatedDate())
//                .build();
//        return questionR.save(question);
//    }
//    //게시글 수정
//    public Question update(QuestionDTO questionDTO){
//        Question question = questionR.findById(questionDTO.getId()).orElseThrow(() -> new RuntimeException("게시글 x"));
//        question.setTitle(questionDTO.getTitle());
//        question.setContent(questionDTO.getContent());
//        return questionR.save(question);
//    }
//    //게시글 삭제
//    public void delete(int id){
//        Question question = questionR.findById(id).orElseThrow(() -> new RuntimeException("게시글 x"));
//        questionR.delete(question);
//    }
}
