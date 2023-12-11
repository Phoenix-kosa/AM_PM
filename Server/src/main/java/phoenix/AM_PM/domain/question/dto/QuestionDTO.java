package phoenix.AM_PM.domain.question.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    private int id;
    private String userId;
    private int projectId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private boolean status;
}
