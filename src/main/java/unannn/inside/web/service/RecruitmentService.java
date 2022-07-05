package unannn.inside.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.recruitment.RecruitmentRepository;
import unannn.inside.domain.user.User;
import unannn.inside.web.dto.recruitment.RecruitmentDto;
import unannn.inside.web.dto.recruitment.question.QuestionDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public RecruitmentDto getRecruitmentForm(UUID recruitmentId) throws Exception {

        Recruitment findRecruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new IllegalArgumentException("모집 공고를 찾을 수 없습니다."));

        RecruitmentDto recruitmentDto = RecruitmentDto.toDto(findRecruitment);

        List<Question> questions = findRecruitment.getQuestions();
        for (Question question : questions) {
            recruitmentDto.getQuestionDtos().add(QuestionDto.toDto(question));
        }
        
        return recruitmentDto;
    }

}
