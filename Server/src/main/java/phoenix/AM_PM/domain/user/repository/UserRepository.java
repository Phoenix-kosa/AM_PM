package phoenix.AM_PM.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
