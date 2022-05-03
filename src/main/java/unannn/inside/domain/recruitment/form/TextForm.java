package unannn.inside.domain.recruitment.form;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("TEXT")
@Entity
public class TextForm  extends Question {
    private Integer minimumLetter;
    private Integer maximumLetter;
}
