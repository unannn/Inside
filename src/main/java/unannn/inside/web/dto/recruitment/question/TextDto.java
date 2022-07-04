package unannn.inside.web.dto.recruitment.question;

import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.form.TextForm;

public class TextDto extends QuestionDto{
    private Integer minimumLetter;
    private Integer maximumLetter;

    public TextDto(TextForm entity){
        super(entity.getQuestionNumber(), entity.getQuestion());
        this.minimumLetter = entity.getMinimumLetter();
        this.maximumLetter = entity.getMaximumLetter();
    }

}
