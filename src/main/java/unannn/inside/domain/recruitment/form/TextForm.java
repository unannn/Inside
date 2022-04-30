package unannn.inside.domain.recruitment.form;

import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("TEXT")
@Entity
public class TextForm  extends Question {
    private Integer minimumLetter;
    private Integer maximumLetter;
}
