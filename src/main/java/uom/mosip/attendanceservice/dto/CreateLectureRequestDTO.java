package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLectureRequestDTO {
    private long courseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int expectedAttendance;
    private long hallId;
    private long lecturerId;
}
