package unannn.inside.domain.application;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Application {
    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    @JoinColumn(name="recruitment_id")
    private Recruitment recruitment;

    @OneToMany(mappedBy = "application")
    private List<Answer> answers = new ArrayList<>();

}
