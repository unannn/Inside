package unannn.inside.domain.recruitment.form;

import javax.persistence.*;

@Entity
public class SelectElement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SelectForm selectForm;

    private String name;
}
