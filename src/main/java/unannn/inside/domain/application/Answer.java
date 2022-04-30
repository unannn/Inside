package unannn.inside.domain.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Recruitment;

import javax.persistence.*;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="question_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public abstract class Answer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;

    private Integer questionNumber;

}
