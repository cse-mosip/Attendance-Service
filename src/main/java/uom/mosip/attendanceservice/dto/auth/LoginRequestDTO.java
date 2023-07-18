package uom.mosip.attendanceservice.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "Grant Type should not be null.")
    @NotBlank(message = "Grant Type should not be blank.")
    private String grant_type;

    @NotBlank(message = "Username should not be blank.")
    @Email(message = "Not a valid Email.")
    private String username;

    @NotBlank(message = "Password should not be blank.")
    private String password;

    @NotBlank(message = "Fingerprint should not be blank.")
    private String fingerprint;
}
