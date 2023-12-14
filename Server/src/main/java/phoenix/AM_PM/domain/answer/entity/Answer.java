package phoenix.AM_PM.domain.answer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
  private int id;
  private String bulletinId; //작성자 ID
  private String title;
  private String content;
  @Column(name = "created_date")
  private LocalDateTime createdDate;

}
