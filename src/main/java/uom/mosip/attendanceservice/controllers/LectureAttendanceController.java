package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.MarkAttendanceRequestDTO;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.LectureAttendanceService;

import java.util.Objects;

@RestController
@RequestMapping(path = "/student/lecture-attendance")
public class LectureAttendanceController {
    private final LectureAttendanceService lectureAttendanceService;

    @Autowired
    public LectureAttendanceController(LectureAttendanceService lectureAttendanceService) {
        this.lectureAttendanceService = lectureAttendanceService;
    }

    @PostMapping(path = "/mark-lecture-attendance")
    public ResponseEntity<ResponseDTO> markLectureAttendance(@RequestBody MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        ResponseDTO responseDTO = lectureAttendanceService.markLectureAttendance(markAttendanceRequestDTO);

        if (Objects.equals(responseDTO.getStatus(), "INVALID_DATA")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
