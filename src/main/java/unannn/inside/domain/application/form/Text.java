package unannn.inside.domain.application.form;

import lombok.NoArgsConstructor;
import unannn.inside.domain.application.Answer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@DiscriminatorValue("TEXT")
@Entity
public class Text extends Answer {
    private String content;

    public Text(Integer  questionNumber, String content) {
        super(questionNumber);
        this.content = content;
    }
}
