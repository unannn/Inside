package unannn.inside.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.recruitment.RecruitmentRepository;
import unannn.inside.domain.user.User;
import unannn.inside.web.dto.recruitment.RecruitmentDto;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

}
