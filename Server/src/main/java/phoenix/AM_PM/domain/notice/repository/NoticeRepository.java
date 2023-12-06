package phoenix.AM_PM.domain.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, String> {
    void deleteById(int id);
}
