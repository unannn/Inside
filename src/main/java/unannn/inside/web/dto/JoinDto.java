package unannn.inside.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import unannn.inside.web.valid.Tel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JoinDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String verifyPassword;

    @NotBlank
    @Email
    private String email;

    @Tel
    private String phoneNumber;
}
