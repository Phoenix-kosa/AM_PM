package phoenix.AM_PM.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import phoenix.AM_PM.domain.answer.entity.Answer;
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
}
