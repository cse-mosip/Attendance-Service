package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.UserDTO;
import uom.mosip.attendanceservice.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping(path = "/get-lecturers")
    public ResponseEntity<ResponseDTO> getLecturers() {
        List<UserDTO> lecturers = userService.getALlUsers();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("OK", "Lecturers Fetched", lecturers));
    }

}
