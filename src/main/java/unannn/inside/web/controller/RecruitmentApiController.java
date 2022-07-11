package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unannn.inside.web.dto.NewQuestionDto;
import unannn.inside.web.dto.recruitment.QuestionType;
import unannn.inside.web.dto.recruitment.RecruitmentDto;
import unannn.inside.web.service.RecruitmentService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RecruitmentApiController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitment/question/new")
    public RecruitmentDto newQuestion(@ModelAttribute NewQuestionDto newQuestionDto) throws Exception {
        UUID recruitmentId = newQuestionDto.getRecruitmentId();
        QuestionType questionType = newQuestionDto.getQuestionType();
        return recruitmentService.addQuestion(recruitmentId, questionType);
    }

}
