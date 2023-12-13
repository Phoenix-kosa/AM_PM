package phoenix.AM_PM.domain.answer.dto;

import lombok.*;
import phoenix.AM_PM.domain.question.entity.Question;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private int id;
    private String bulletinId; //작성자 ID
    private String title;
    private String content;
    private String createdDate;
    private Question question;
    public void chquestion(Question question){
        this.question = question;
    }
}
