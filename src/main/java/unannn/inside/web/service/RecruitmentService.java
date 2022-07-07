package unannn.inside.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unannn.inside.domain.application.form.Text;
import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.recruitment.RecruitmentRepository;
import unannn.inside.domain.recruitment.form.CheckboxForm;
import unannn.inside.domain.recruitment.form.SelectForm;
import unannn.inside.domain.recruitment.form.TextForm;
import unannn.inside.domain.user.User;
import unannn.inside.web.dto.recruitment.QuestionType;
import unannn.inside.web.dto.recruitment.RecruitmentDto;
import unannn.inside.web.dto.recruitment.question.QuestionDto;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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

    @Transactional
    public RecruitmentDto addQuestion(UUID recruitmentId, QuestionType questionType) throws Exception {
        Recruitment findRecruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new IllegalArgumentException("모집 공고를 찾을 수 없습니다."));

        RecruitmentDto recruitmentDto = RecruitmentDto.toDto(findRecruitment);

        //새 Quiestion 추가
        int newQuestionNumber = findRecruitment.getQuestions().size() + 1;

        Question newQuestion = null;
        if (questionType == QuestionType.TEXT){
            newQuestion = new TextForm(0,0);
        } else if (questionType == QuestionType.CHECKBOX) {
            List<String> newElements = new ArrayList<>();
            newElements.add("새 항목");
            newQuestion = new CheckboxForm(newElements);
        }else if (questionType == QuestionType.SELECT){
            List<String> newElements = new ArrayList<>();
            newElements.add("새 항목");
            newQuestion = new SelectForm(newElements);
        }

        if (newQuestion == null){
            throw new IllegalArgumentException("지원하지 않는 질문 타입입니다.");
        }

        newQuestion.setQuestion(newQuestionNumber, "새 질문");
        findRecruitment.addQuestion(newQuestion);


        List<Question> questions = findRecruitment.getQuestions();
        for (Question question : questions) {
            recruitmentDto.getQuestionDtos().add(QuestionDto.toDto(question));
        }

        return recruitmentDto;
    }

}
