package phoenix.AM_PM.domain.chat.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import phoenix.AM_PM.domain.chat.entity.Chat;

import java.util.List;

public interface ChatRepositoryCustom {
    Slice<Chat> chatPage(Pageable pageable, Integer projectId, Integer cursorId);
}
