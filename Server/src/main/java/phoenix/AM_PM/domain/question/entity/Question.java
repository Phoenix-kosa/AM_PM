package phoenix.AM_PM.domain.question.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@Getter
@ToString
@NoArgsConstructor

public class Question {
  private int id;
  private String userId;
  private int projectId;
  private String title;
  private String content;
  private LocalDateTime createdDate;
  private boolean status;

  // Constructors, getters, and setters
}