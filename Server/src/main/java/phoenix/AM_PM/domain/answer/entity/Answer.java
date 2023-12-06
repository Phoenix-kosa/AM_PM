package phoenix.AM_PM.domain.answer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Answer {

  private int id;
  private int bulletinId;
  private String title;
  private String content;

  @Column(name = "created_date")
  @CreatedDate
  private LocalDateTime createdDate;


}
