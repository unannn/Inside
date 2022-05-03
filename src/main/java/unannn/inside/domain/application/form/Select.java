package unannn.inside.domain.application.form;

import unannn.inside.domain.application.Answer;
import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("SELECT")
@Entity(name = "SELECTS")
public class Select extends Answer {
    private String value;
}
