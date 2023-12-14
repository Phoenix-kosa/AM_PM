package phoenix.AM_PM.domain.ganttchart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="task")
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int ganttId;
  private int projectId;
  private String userId;
  private LocalDateTime beginDate;
  private LocalDateTime EndDate;
  private String content;
  private String backgroundColor;
  private String fontColor;
}