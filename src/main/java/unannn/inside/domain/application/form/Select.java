package unannn.inside.domain.application.form;

import lombok.NoArgsConstructor;
import unannn.inside.domain.application.Answer;
import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@DiscriminatorValue("SELECT")
@Entity(name = "selects")
public class Select extends Answer {
    private String value;
    public Select(Integer questionNumber, String value) {
        super(questionNumber);
        this.value = value;
    }
}
