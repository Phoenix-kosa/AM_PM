package phoenix.AM_PM.domain.answer.dto;

import lombok.*;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.user.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddAnswerRequest {

    private String title;
    private String content;

    public Answer toEntity() {
        return Answer.builder()
                .title(title)
                .content(content)
                .build();
    }
}
