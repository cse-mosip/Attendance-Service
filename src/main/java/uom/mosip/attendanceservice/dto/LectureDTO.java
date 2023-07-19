package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uom.mosip.attendanceservice.dto.lms.CourseDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    private long id;
    private CourseDTO course;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isStarted;
    private boolean isEnded;
    private int expectedAttendance;
    private int attendance;
    private String hallName;
    private String lecturerName;
}
