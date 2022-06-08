package unannn.inside.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import unannn.inside.web.valid.Password;
import unannn.inside.web.valid.Tel;
import unannn.inside.web.valid.Username;

import javax.validation.constraints.*;

@Getter
@Setter
public class JoinDto {

    @NotBlank
    @Username
    private String username;


    @NotBlank
    @Password
    private String password;

    @NotBlank
    private String verifyPassword;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

//    @Tel
//    private String phoneNumber;
}
