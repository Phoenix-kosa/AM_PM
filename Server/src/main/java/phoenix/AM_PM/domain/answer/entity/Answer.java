package phoenix.AM_PM.domain.answer.entity;

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
public class Answer {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;
  private int bulletinId; //작성자 ID
  private String title;
  private String content;
  private LocalDateTime createdDate;
}
