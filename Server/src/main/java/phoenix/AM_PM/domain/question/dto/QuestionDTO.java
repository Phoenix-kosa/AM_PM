package phoenix.AM_PM.domain.question.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class QuestionDTO {
    private int id;
    private String userId;
    private int projectId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private boolean status;
}
