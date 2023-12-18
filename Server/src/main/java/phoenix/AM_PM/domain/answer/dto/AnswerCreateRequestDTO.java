package phoenix.AM_PM.domain.answer.dto;

import lombok.Data;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.user.entity.User;

@Data
public class AnswerCreateRequestDTO {

    private String content;

    public Answer toEntity(Question question, User user) {
        return Answer.builder()
                .user(user)
                .question(question)
                .content(content)
                .build();
    }
}
