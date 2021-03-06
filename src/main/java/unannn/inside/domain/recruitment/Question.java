package unannn.inside.domain.recruitment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    private Integer questionNumber;

    private String question;

    public void setQuestion(int questionNumber, String question){
        this.questionNumber = questionNumber;
        this.question = question;
    }

    protected void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }
}
