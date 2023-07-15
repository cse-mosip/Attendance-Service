package uom.mosip.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminRegisterDTO {
    private String name;
    private String email;
    private String message;

    public AdminRegisterDTO(String message) {
        this.message = message;
    }
}
