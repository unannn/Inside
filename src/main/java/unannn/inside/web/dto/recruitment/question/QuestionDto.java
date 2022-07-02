package unannn.inside.web.dto.recruitment.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jboss.jandex.ClassType;
import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.form.CheckboxForm;
import unannn.inside.domain.recruitment.form.SelectForm;
import unannn.inside.domain.recruitment.form.TextForm;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class QuestionDto {
    private int questionNumber;
    private String question;


    public static QuestionDto toDto(Question entity){
        QuestionDto questionDto = new QuestionDto(entity.getQuestionNumber(), entity.getQuestion());
        if(entity instanceof TextForm){
            TextForm textForm = (TextForm) entity;
        } else if (entity instanceof CheckboxForm) {
            CheckboxForm textForm = (CheckboxForm) entity;
        } else if(entity instanceof SelectForm){
            SelectForm textForm = (SelectForm) entity;
        }

        return questionDto;
    }
}
