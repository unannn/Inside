package unannn.inside.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import unannn.inside.web.valid.Tel;

import javax.validation.constraints.*;

@Getter
@Setter
public class JoinDto {

    @NotBlank
    @Pattern(regexp = "[a-z\\d_-]{5,20}", message = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 가능합니다")
    private String username;

    @NotBlank
    @Pattern(regexp = "[a-z\\d!@#$%^&*_+=-]{8,20}", message = "8~20자의 영문 소문자, 숫자와 특수기호(!@#$%^&*_-+=)만 가능합니다.")
    private String password;

    @NotBlank
    private String verifyPassword;

    @NotBlank
    @Email
    private String email;

    @Tel
    private String phoneNumber;
}
