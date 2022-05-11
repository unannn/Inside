package unannn.inside.domain.application.form;

import lombok.NoArgsConstructor;
import unannn.inside.domain.application.Answer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@DiscriminatorValue("CHECKBOX")
@Entity
public class Checkbox extends Answer {
    @OneToMany(mappedBy = "checkbox")
    private List<CheckboxValue> elements = new ArrayList<>();

}
