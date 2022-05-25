package unannn.inside.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
