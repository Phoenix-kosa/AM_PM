package phoenix.AM_PM.domain.chat.repository;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.chat.entity.Chat;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    // 메시지 읽기 project_id 기반
    Optional<Chat> findByProjectId(Integer projectId);
}