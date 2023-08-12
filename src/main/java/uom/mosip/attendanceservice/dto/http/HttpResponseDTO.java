package uom.mosip.attendanceservice.dto.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpResponseDTO {

    private int code;
    private String message;
    private boolean isSuccessful;
    private boolean exceptionOccurred;
    private String requestBody;

    public HttpResponseDTO(int code, String message, boolean isSuccessful) {
        this.code = code;
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.exceptionOccurred = false;
    }

    public HttpResponseDTO(boolean exceptionOccurred) {
        this.isSuccessful = false;
        this.exceptionOccurred = true;
    }
}
