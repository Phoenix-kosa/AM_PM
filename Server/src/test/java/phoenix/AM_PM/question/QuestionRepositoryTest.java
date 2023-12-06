package phoenix.AM_PM.question;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import phoenix.AM_PM.domain.question.repository.QuestionRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class QuestionRepositoryTest {
    @Autowired
    QuestionRepository QuestionRepository;
    @BeforeEach()
    void pr(){ System.out.println("=".repeat(80)); }
    @Test
    @Order(1)
    public void pp(){

    }
}
