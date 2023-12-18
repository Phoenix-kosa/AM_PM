package phoenix.AM_PM.domain.question.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.question.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByUserId(String userId);

}
