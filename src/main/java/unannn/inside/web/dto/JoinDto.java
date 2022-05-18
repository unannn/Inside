package unannn.inside.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {
    private String username;
    private String password;
    private String verifyPassword;
    private String email;
    private String phoneNumber;
}
