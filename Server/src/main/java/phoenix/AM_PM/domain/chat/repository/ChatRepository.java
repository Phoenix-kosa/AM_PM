package phoenix.AM_PM.domain.chat.repository;

import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.chat.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    // 메시지 읽기 project_id 기반
    Page<Chat> findByProjectIdOrderByCreatedDateDesc(Integer projectId, Pageable pageable);
}