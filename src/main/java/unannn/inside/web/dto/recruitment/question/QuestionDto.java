package unannn.inside.web.dto.recruitment.question;

import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.form.TextForm;

import java.util.List;

public abstract class QuestionDto {
    private int questionNumber;
    private String question;

    abstract QuestionDto toDto(Question entity);
}
