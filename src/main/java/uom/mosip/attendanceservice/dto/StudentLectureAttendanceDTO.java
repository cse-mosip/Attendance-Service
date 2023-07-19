package uom.mosip.attendanceservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentLectureAttendanceDTO {
    private LectureDTO lecture;
    private LocalDateTime arrivalTime;
    private long lectureAttendanceID;

    public StudentLectureAttendanceDTO(LectureDTO lecture, LocalDateTime arrivalTime, long lectureAttendanceID) {
        this.lecture = lecture;
        this.arrivalTime = arrivalTime;
        this.lectureAttendanceID = lectureAttendanceID;
    }
}
