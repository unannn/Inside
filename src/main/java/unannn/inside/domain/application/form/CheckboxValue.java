package unannn.inside.domain.application.form;

import lombok.NoArgsConstructor;
import unannn.inside.domain.BooleanToYNConverter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class CheckboxValue {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Checkbox checkbox;

    private String name;

    @Convert(converter = BooleanToYNConverter.class)
    private Boolean checked;
}
