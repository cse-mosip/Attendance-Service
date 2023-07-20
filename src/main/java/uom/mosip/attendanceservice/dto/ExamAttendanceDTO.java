package uom.mosip.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import uom.mosip.attendanceservice.models.ExamAttendance;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExamAttendanceDTO {
    private String studentId;
    private String indexNo;
    private String studentName;
    private String picture;
    private boolean isPresent;
    private LocalDateTime markedTime;
    private boolean isValidated;
    private LocalDateTime validatedTime;

    public ExamAttendanceDTO(String studentId, ExamAttendance examAttendance, StudentDTO studentDTO) {
        this.studentId = studentId;

        if (examAttendance != null) {
            this.isPresent = true;
            this.markedTime = examAttendance.getMarkedTime();
            this.isValidated = examAttendance.isValidated();
            this.validatedTime = examAttendance.getValidatedTime();
        } else {
            this.isPresent = false;
            this.isValidated = false;
        }

        if (studentDTO != null) {
            this.studentName = studentDTO.getName();
            this.indexNo = studentDTO.getIndex_number();
            this.picture = studentDTO.getPicture();
        }
    }

}
