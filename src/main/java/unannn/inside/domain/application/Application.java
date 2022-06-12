package unannn.inside.domain.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Application {
    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="recruitment_id")
    private Recruitment recruitment;

    @OneToMany(mappedBy = "application", cascade = CascadeType.PERSIST)
    private List<Answer> answers = new ArrayList<>();

    public void storeAnswer(Answer answer) {
        if (answer.getApplication() != null) {
            answer.getApplication().getAnswers().remove(answer);
        }
        answer.setApplication(this);
        answers.add(answer);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
