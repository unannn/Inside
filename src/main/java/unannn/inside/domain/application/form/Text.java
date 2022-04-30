package unannn.inside.domain.application.form;

import unannn.inside.domain.application.Answer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("TEXT")
@Entity
public class Text extends Answer {
    private String content;
}
