package uom.mosip.attendanceservice.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "Username should not be null.")
    @NotBlank(message = "Username should not be blank.")
    private String username;
    @NotNull(message = "Password should not be null.")
    @NotBlank(message = "Password should not be blank.")
    private String password;
}
