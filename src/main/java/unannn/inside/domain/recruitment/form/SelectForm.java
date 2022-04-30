package unannn.inside.domain.recruitment.form;

import unannn.inside.domain.recruitment.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("SELECT")
@Entity
public class SelectForm extends Question {

    @OneToMany(mappedBy = "selectForm")
    private List<SelectElement> elements = new ArrayList<>();
}
