package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.models.LectureAttendance;
import uom.mosip.attendanceservice.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    private long id;
    private String moduleCode;
    private String moduleName;
    private int intake;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isStarted;
    private boolean isEnded;
    private int expectedAttendance;
    private int attendance;
    private Hall hall;
    private User lecturer;
    private List<LectureAttendance> attendees;
}
