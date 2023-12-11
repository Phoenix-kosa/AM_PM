package phoenix.AM_PM.domain.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.answer.repository.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;



}
