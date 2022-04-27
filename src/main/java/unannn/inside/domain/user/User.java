package unannn.inside.domain.user;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String password;
    private String userName;

    public User(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }
}
