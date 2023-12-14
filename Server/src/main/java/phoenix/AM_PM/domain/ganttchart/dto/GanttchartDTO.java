package phoenix.AM_PM.domain.ganttchart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GanttchartDTO {
    private int projectId;
    private String title;
    private String content;
}
