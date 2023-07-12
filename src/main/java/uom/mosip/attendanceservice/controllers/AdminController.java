package uom.mosip.attendanceservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ResponseDTO;


@RestController
public class AdminController {
    @GetMapping("/admin/lecture-attendance/student ")
    public ResponseDTO healthCheck() {
        return null;
    }

}
