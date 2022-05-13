package unannn.inside.domain.recruitment.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@DiscriminatorValue("CHECKBOX")
@NoArgsConstructor
@Table(name = "checkbox_form")
@Entity
public class CheckboxForm extends Question {
    @OneToMany(mappedBy = "checkboxForm")
    private List<CheckboxElement> elements = new ArrayList<>();

    public CheckboxForm(List<String> elementNames) {
        for (String elementName : elementNames) {
            elements.add(new CheckboxElement(elementName));
        }
    }
}
