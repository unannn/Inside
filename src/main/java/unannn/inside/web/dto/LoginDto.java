package unannn.inside.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LoginDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
