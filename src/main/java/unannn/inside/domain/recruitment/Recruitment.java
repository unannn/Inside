package unannn.inside.domain.recruitment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import unannn.inside.domain.user.User;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class Recruitment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
