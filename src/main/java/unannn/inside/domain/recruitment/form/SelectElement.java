package unannn.inside.domain.recruitment.form;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="select_element")
@Entity
public class SelectElement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SelectForm selectForm;

    private String element;

    public SelectElement(String element) {
        this.element = element;
    }
}
