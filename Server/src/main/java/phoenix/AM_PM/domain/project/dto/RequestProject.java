package phoenix.AM_PM.domain.project.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class RequestProject {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String content;
}
