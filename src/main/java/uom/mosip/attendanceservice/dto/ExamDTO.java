package uom.mosip.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import uom.mosip.attendanceservice.models.Hall;
import uom.mosip.attendanceservice.models.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
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
    private User invigilator;
}
