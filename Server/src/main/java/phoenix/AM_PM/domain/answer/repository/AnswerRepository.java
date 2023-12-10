package phoenix.AM_PM.domain.answer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.answer.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer>
}
