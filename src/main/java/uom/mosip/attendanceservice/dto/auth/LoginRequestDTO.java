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

    @NotNull(message = "Grant Type should not be null.")
    @NotBlank(message = "Grant Type should not be blank.")
    private String grant_type;

    private String username;

    private String password;

    private String fingerprint;
}
