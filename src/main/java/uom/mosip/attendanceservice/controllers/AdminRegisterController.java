package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.AdminRegisterDTO;
import uom.mosip.attendanceservice.models.User;
import uom.mosip.attendanceservice.services.UserService;

@RestController
public class AdminRegisterController {

    @Autowired
    private final UserService userService;

    public AdminRegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/register")
    public ResponseEntity<AdminRegisterDTO> registerAdmin(@RequestBody User user) {
        if (user.getUserType() == 1) { // admin
            String encryptedPassword = encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            try {
                User savedUser = userService.saveUser(user);
                return new ResponseEntity<>(new AdminRegisterDTO(savedUser.getName(), savedUser.getEmail(), "Successfully created"), HttpStatus.CREATED);
            } catch(Exception except) {
                return new ResponseEntity<>(new AdminRegisterDTO( "Conflict in creating the admin user"), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(new AdminRegisterDTO("Request header does not have the required field values"), HttpStatus.BAD_REQUEST);
        }
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}