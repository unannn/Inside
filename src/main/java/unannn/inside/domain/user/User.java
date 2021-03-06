package unannn.inside.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import unannn.inside.domain.application.Application;
import unannn.inside.domain.recruitment.Recruitment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String encodedPassword;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Recruitment> recruitments = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Application> applications = new ArrayList<>();

    @Builder
    public User(String email, String username, String name, String phoneNumber, String encodedPassword) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.encodedPassword = encodedPassword;
    }

    public void register(Recruitment recruitment) {
        if(recruitment.getUser() != null){
            recruitment.getUser().getRecruitments().remove(recruitment);
        }
        this.recruitments.add(recruitment);
        recruitment.setUser(this);
    }

    public void register(Application application) {
        if(application.getUser() != null){
            application.getUser().getApplications().remove(application);
        }
        this.applications.add(application);
        application.setUser(this);
    }

}
