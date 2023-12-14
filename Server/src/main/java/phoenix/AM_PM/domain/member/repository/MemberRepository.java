package phoenix.AM_PM.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.member.entity.Members;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Integer> {
    List<Members> findAllByUserId(Integer userId);
    Optional<Members> findAllByUserIdAndProjectId(Integer userId, Integer projectId);
}