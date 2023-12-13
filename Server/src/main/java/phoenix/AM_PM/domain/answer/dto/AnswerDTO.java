package phoenix.AM_PM.domain.answer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AnswerDTO {
    private int id;
    private int bulletinId; //작성자 ID
    private String title;
    private String content;
    private LocalDateTime createdDate;
}
