package unannn.inside.domain.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Recruitment;

import javax.persistence.*;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="answer_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public abstract class Answer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    private Integer answerNumber;
    public Answer(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
