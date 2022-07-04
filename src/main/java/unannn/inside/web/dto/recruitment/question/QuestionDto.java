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
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class QuestionDto {
    private int questionNumber;
    private String question;


    public static QuestionDto toDto(Question entity) throws Exception {

        QuestionDto questionDto = null;

        if(entity instanceof TextForm){
            TextForm textForm = (TextForm) entity;
            questionDto = new TextDto(textForm);

        } else if (entity instanceof CheckboxForm) {
            CheckboxForm checkboxForm = (CheckboxForm) entity;
            questionDto = new CheckboxDto(checkboxForm);


        } else if(entity instanceof SelectForm){
            SelectForm textForm = (SelectForm) entity;
            questionDto = new SelectDto(textForm);
        }

        return questionDto;
    }
}
