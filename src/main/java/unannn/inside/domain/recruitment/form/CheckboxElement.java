package unannn.inside.domain.recruitment.form;

import javax.persistence.*;

@Entity
public class CheckboxElement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private CheckboxForm checkboxForm;
    private String name;

    public CheckboxElement(String name) {
        this.name = name;
    }
}
