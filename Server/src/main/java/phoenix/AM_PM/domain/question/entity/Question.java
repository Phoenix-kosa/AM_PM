package phoenix.AM_PM.domain.question.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Question {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;
  private String userId;
  private int projectId;
  private String title;
  private String content;
  private LocalDateTime createdDate;
  private boolean status;

  // Constructors, getters, and setters
}