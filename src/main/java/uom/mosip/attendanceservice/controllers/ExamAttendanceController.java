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
import uom.mosip.attendanceservice.services.ExamAttendanceService;

import java.util.Objects;

@RestController
@RequestMapping(path = "/student/exam-attendance")
public class ExamAttendanceController {
    private final ExamAttendanceService examAttendanceService;

    @Autowired
    public ExamAttendanceController(ExamAttendanceService examAttendanceService) {
        this.examAttendanceService = examAttendanceService;
    }

    @PostMapping(path = "/mark-exam-attendance")
    public ResponseEntity<ResponseDTO> markExamAttendance(@RequestBody MarkAttendanceRequestDTO markAttendanceRequestDTO) {
        ResponseDTO responseDTO = examAttendanceService.markExamAttendance(markAttendanceRequestDTO);

        if (Objects.equals(responseDTO.getStatus(), "INVALID_DATA")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
