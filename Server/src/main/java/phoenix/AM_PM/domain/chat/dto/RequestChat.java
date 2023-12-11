package phoenix.AM_PM.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import phoenix.AM_PM.domain.chat.entity.Chat;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class RequestChat {
    private String message;
    private Integer projectId;
    private String userId;
}
