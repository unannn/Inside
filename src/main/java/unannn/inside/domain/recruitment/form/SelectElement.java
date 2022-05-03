package unannn.inside.domain.recruitment.form;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectElement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SelectForm selectForm;

    private String name;

    public SelectElement(String name) {
        this.name = name;
    }
}
