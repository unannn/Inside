package unannn.inside.domain.recruitment;

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
public abstract class Question {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recruitment recruitment;

    private Integer questionNumber;

    private String question;

}
