package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uom.mosip.attendanceservice.dto.lms.CourseDTO;
import uom.mosip.attendanceservice.models.ExamAttendance;
import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private long id;
    private CourseDTO course;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isStarted;
    private boolean isEnded;
    private int expectedAttendance;
    private int attendance;
    private Hall hall;
    private String lecturerName;
    private List<ExamAttendance> attendees;
}
