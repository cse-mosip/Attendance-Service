package uom.mosip.attendanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uom.mosip.attendanceservice.models.User;
import uom.mosip.attendanceservice.services.UserService;

@RestController
@RequestMapping("/api")
public class AdminRegisterController {

    @Autowired
    private final UserService userService;

    public AdminRegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/register")
    public ResponseEntity<User> registerAdmin(@RequestBody User user) {
        if (user.getUserType() == 1) { // admin
            String encryptedPassword = encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            try {
                User savedUser = userService.saveUser(user);
                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
            } catch(Exception except) {
                return new ResponseEntity<>(user, HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}