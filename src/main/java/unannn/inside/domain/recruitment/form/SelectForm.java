package unannn.inside.domain.recruitment.form;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("SELECT")
@Table(name = "select_form")
@Entity
public class SelectForm extends Question {

    @OneToMany(mappedBy = "selectForm",cascade = CascadeType.ALL)
    private List<SelectElement> elements = new ArrayList<>();

    public SelectForm(List<String> elementNames) {
        for (String elementName : elementNames) {
            elements.add(new SelectElement(elementName));
        }
    }
}
