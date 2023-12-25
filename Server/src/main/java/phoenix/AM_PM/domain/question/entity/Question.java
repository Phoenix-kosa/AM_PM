package phoenix.AM_PM.domain.question.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import phoenix.AM_PM.domain.user.entity.User;

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

  @Column(name="user_id")
  private String userId;
  private int projectId;
  private String title;
  private String content;

  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdDate;

  private boolean status;

//  @ManyToOne
//  @JoinColumn(name = "user_id")
//  private User user;

  // Constructors, getters, and setters
}