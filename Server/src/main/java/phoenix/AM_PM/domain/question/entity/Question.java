package phoenix.AM_PM.domain.question.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import phoenix.AM_PM.domain.answer.entity.Answer;
import phoenix.AM_PM.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String userId;
  private int projectId;
  private String title;
  private String content;
  private LocalDateTime createdDate;
  private boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user; // 작성자

  @OneToMany(mappedBy = "board", orphanRemoval = true)
  private List<Answer> comments; // 댓글
  // Constructors, getters, and setters
}