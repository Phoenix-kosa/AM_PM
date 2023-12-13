package phoenix.AM_PM.domain.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import phoenix.AM_PM.domain.project.dto.RequestProject;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String title;
  private String content;

  public static Project from(RequestProject requestProject) {
    return new Project(null,
            requestProject.getStartDate(),
            requestProject.getEndDate(),
            requestProject.getTitle(),
            requestProject.getContent());
  }

  public void updateTitle(String title) {
    this.title = title;
  }
  public void updateContent(String content) {
    this.content = content;
  }
  public void updateStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }
  public void updateEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  // Constructors, getters, and setters
}