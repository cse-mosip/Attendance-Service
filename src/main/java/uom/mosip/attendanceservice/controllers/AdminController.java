package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.LectureAttendance;
import uom.mosip.attendanceservice.services.AdminService;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/lecture-attendance/student/{studentId}")
    public ResponseEntity<ResponseDTO> getStudentAttendance(@PathVariable String studentId) {
        ResponseDTO responseDTO = new ResponseDTO();

        List<LectureAttendance> attendanceList = adminService.getStudentAttendance(studentId);

        responseDTO.setData(attendanceList);
        responseDTO.setStatus("200");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/admin/lecture-attendance/lecture/{lectureId}")
    public ResponseEntity<ResponseDTO> getLectureAttendance(@PathVariable("lectureId") long lectureId) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<LectureAttendance> attendanceList = adminService.getLectureAttendance(lectureId);
        if (attendanceList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("404", "Lecture ID is not found."));
        }
        responseDTO.setData(attendanceList);
        responseDTO.setStatus("200");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

}
