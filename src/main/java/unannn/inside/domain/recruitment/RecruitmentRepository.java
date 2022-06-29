package unannn.inside.domain.recruitment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface RecruitmentRepository extends JpaRepository<Recruitment, UUID> {

}
