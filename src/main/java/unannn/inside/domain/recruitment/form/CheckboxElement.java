package unannn.inside.domain.recruitment.form;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "checkbox_element")
@Entity
public class CheckboxElement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CheckboxForm checkboxForm;
    private String element;

    public CheckboxElement(String element) {
        this.element = element;
    }
}
