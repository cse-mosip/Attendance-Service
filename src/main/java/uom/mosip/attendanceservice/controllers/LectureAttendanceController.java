package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.MarkAttendanceRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.LectureAttendanceService;

@RestController
@RequestMapping(path = "student/lecture-attendance")
public class LectureAttendanceController {
    private final LectureAttendanceService lectureAttendanceService;

    @Autowired
    public LectureAttendanceController(LectureAttendanceService lectureAttendanceService) {
        this.lectureAttendanceService = lectureAttendanceService;
    }

    @PostMapping(path = "mark-lecture-attendance")
    public ResponseDTO markLectureAttendance(@RequestBody MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        return lectureAttendanceService.markLectureAttendance(markAttendanceRequestDTO);
    }
}
