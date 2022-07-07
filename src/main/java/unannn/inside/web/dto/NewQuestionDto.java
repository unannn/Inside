package unannn.inside.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import unannn.inside.web.dto.recruitment.QuestionType;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class NewQuestionDto {
    private UUID recruitmentId;
    private QuestionType questionType;
}
