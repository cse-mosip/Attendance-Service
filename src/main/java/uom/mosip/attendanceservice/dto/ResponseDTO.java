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
public class ResponseDTO {
    private String status;
    private String message;
    private Object data;

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
