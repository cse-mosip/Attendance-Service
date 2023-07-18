package uom.mosip.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LectureUpdateRequestDTO {
    private Long lectureId;
    private long courseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isStarted;
    private boolean isEnded;
    private int expectedAttendance;
    private int attendance;
}