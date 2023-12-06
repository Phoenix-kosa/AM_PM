package phoenix.AM_PM.domain.question.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import phoenix.AM_PM.domain.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query

}
