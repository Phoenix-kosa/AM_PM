package phoenix.AM_PM.domain.answer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import phoenix.AM_PM.domain.question.entity.Question;
import phoenix.AM_PM.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;
  private String bulletinId; //작성자 ID
  private String title;
  private String content;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @ManyToOne
  @JoinColumn
  private Question question;

  @ManyToOne
  @JoinColumn
  private User user;

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
  public void changeUserId(User userId){
    this.user = userId;
  }
  public void changePost(Question question){
    this.question = question;
  }
}
