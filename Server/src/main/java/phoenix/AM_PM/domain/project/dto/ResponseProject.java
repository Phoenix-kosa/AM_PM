package phoenix.AM_PM.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import phoenix.AM_PM.domain.project.entity.Project;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ResponseProject {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String content;

    public static ResponseProject of(Project project) {
        return new ResponseProject(project.getId(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTitle(),
                project.getContent());
    }
}
