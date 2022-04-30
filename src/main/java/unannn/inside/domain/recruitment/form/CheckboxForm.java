package unannn.inside.domain.recruitment.form;

import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("CHECKBOX")
@Entity
public class CheckboxForm extends Question {
    @OneToMany(mappedBy = "checkboxForm")
    private List<CheckboxElement> elements = new ArrayList<>();

}
