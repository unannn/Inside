package unannn.inside.domain.recruitment;

import lombok.*;
import unannn.inside.domain.user.User;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@NoArgsConstructor
@Entity
public class Recruitment {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", name = "recruitment_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "recruitment",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @Builder
    private Recruitment(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addQuestion(Question question) {
        if (question.getRecruitment() != null) {
            question.getRecruitment().getQuestions().remove(question);
        }
        this.questions.add(question);
        question.setRecruitment(this);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
