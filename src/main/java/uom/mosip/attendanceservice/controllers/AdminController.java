package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.services.AdminService;


@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/lecture-attendance/student/{studentId}")
    public ResponseDTO getStudentAttendance(@PathVariable String studentId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(adminService.getStudentAttendance(studentId));
        responseDTO.setStatus("200");
        return responseDTO;
    }
   @GetMapping("/admin/lecture-attendance/lecture ")
    public ResponseDTO getLectureAttendance() {
        return null;
    }


}
