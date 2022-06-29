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
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentDto getRecruitmentForm(UUID recruitmentId) {

        Recruitment findRecruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new IllegalArgumentException("모집 공고를 찾을 수 없습니다."));

        return RecruitmentDto.toDto(findRecruitment);
    }

}
