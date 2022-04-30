package unannn.inside.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String email;
    private String userName;
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Recruitment> recruitments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();


    public User(String email, String userName, String phoneNumber) {
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
}
