package uom.mosip.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureAttendanceDTO {
    private String studentId;
    private String indexNo;
    private String studentName;
    private String picture;
    private boolean isPresent;
    private LocalDateTime arrivalTime;

    public LectureAttendanceDTO(String studentId, LectureAttendance lectureAttendance, StudentDTO studentDTO) {
        this.studentId = studentId;

        if (lectureAttendance != null) {
            this.isPresent = true;
            this.arrivalTime = lectureAttendance.getArrivalTime();
        } else {
            this.isPresent = false;
        }
        if (studentDTO != null) {
            this.studentName = studentDTO.getName();
            this.indexNo = studentDTO.getIndex_number();
            this.picture = studentDTO.getPicture();
        }
    }

}
