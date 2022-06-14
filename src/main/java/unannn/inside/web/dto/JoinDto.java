package unannn.inside.web.dto;

import lombok.*;
import unannn.inside.web.valid.Password;
import unannn.inside.web.valid.Tel;
import unannn.inside.web.valid.Username;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
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

    @Builder

    public JoinDto(String username, String password, String verifyPassword, String name, String email) {
        this.username = username;
        this.password = password;
        this.verifyPassword = verifyPassword;
        this.name = name;
        this.email = email;
    }
    //    @Tel
//    private String phoneNumber;
}
