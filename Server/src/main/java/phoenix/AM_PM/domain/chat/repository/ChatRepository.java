package phoenix.AM_PM.domain.chat.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.repository.querydsl.ChatRepositoryCustom;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, Integer>, ChatRepositoryCustom {
    // 메시지 읽기 project_id 기반
    Slice<Chat> findByProjectIdOrderByCreatedDateDesc(Integer projectId, Pageable pageable);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Chat> findByProjectIdOrderByCreatedDate(Integer projectId);
}