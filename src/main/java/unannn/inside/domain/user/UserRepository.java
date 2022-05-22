package unannn.inside.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional(readOnly = true)
    public User findByUsername(String username);
}
