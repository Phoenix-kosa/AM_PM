package phoenix.AM_PM.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import phoenix.AM_PM.domain.chat.entity.Chat;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor
public class ResponseChat {
    private Integer id;
    private String userId;
    private String nickName;
    private String profileImg;
    private String message;
    private LocalDateTime createdDate;
    private Integer unread;
    private String whoRead;

    public static ResponseChat from(Chat chat) {
        return new ResponseChat(chat.getId(), chat.getUser().getUserId(), chat.getUser().getNickname(),
                chat.getUser().getProfileImg(), chat.getMessage(), chat.getCreatedDate(), chat.getUnread(), chat.getWhoRead());
    }
}
