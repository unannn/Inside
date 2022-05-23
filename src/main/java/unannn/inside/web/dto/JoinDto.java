package unannn.inside.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import unannn.inside.web.valid.Tel;

import javax.validation.constraints.*;

@Getter
@Setter
public class JoinDto {

    @Pattern(regexp = "[a-z\\d_-]{5,20}")
    private String username;

    @Pattern(regexp = "[a-z\\d!@#$%^&*_+=-]{8,20}")
    private String password;

    @NotBlank
    private String verifyPassword;

    @NotBlank
    @Email
    private String email;

    @Tel
    private String phoneNumber;
}
