package unannn.inside.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import unannn.inside.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
