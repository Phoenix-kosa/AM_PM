package phoenix.AM_PM.domain.question.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.AM_PM.domain.question.entity.Question;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class AddQuestion {
    private int id;
    private String userId;
    private int projectId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private boolean status;

}
