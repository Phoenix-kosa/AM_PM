package phoenix.AM_PM.domain.answer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phoenix.AM_PM.domain.answer.entity.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    //조회
    List<Answer> findAll();
}
