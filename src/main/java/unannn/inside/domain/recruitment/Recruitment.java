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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "recruitment")
    private List<Question> questions = new ArrayList<>();

    @Builder
    private Recruitment(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getRecruitments().remove(this);
        }
        this.user = user;
        user.getRecruitments().add(this);
    }


}
