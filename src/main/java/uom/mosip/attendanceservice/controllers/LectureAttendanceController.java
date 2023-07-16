package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.MarkAttendanceRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.LectureAttendanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "student/lecture-attendance")
public class LectureAttendanceController {
    private final LectureAttendanceService lectureAttendanceService;

    @Autowired
    public LectureAttendanceController(LectureAttendanceService lectureAttendanceService) {
        this.lectureAttendanceService = lectureAttendanceService;
    }

    @PostMapping(path = "mark-lecture-attendance")
    public ResponseEntity<ResponseDTO> markLectureAttendance(@RequestBody MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        return ResponseEntity.ok().body(lectureAttendanceService.markLectureAttendance(markAttendanceRequestDTO));
    }
}
