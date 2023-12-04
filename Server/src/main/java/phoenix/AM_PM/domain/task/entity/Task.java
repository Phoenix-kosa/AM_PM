package phoenix.AM_PM.domain.task.entity;

import java.time.LocalDateTime;

public class Task {
  private int id;
  private int ganttId;
  private int projectId;
  private String userId;
  private LocalDateTime beginDate;
  private LocalDateTime endDate;
  private String content;
  private String backgroundColor;
  private String frontColor;

  // Constructors, getters, and setters
}