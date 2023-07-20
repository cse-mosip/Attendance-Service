package uom.mosip.attendanceservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.dto.auth.LoginRequestDTO;
import uom.mosip.attendanceservice.helpers.UserTypeHelper;
import uom.mosip.attendanceservice.models.User;
import uom.mosip.attendanceservice.services.AuthenticationService;
import uom.mosip.attendanceservice.services.TokenService;
import uom.mosip.attendanceservice.services.UserService;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserTypeHelper userTypeHelper;

    @PostMapping("/admin/login")
    public Object login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        if (loginRequestDTO.getGrant_type().equals("password")) {
            return handlePasswordLogin(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        } else if (loginRequestDTO.getGrant_type().equals("fingerprint")) {
            return handleFingerprintLogin(loginRequestDTO.getFingerprint());
        }
        else {
            return ResponseEntity.badRequest().body(new ResponseDTO("INVALID_GRANT", "Invalid grant type."));
        }
    }

    private Object handlePasswordLogin(String username, String password) {
        Optional<User> user = userService.getUserByEmail(username);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("EMAIL_NOT_FOUND", "Email cannot be found."));
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("INCORRECT_PASSWORD", "Incorrect Password."));
        }

        return tokenService.generateJWTToken(String.valueOf(user.get().getId()),
                String.valueOf(user.get().getUserType()),
                user.get().getId(),
                userTypeHelper.getUserTypeName(user.get().getUserType()));
    }

    private Object handleFingerprintLogin(String fingerprint) {
        String mosipId = authenticationService.authenticate(fingerprint);

        if (mosipId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("USER_NOT_FOUND", "Fingerprint does not match with a user."));
        }

        Optional<User> user = userService.getUserByMosipID(mosipId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO("USER_NOT_FOUND", "Fingerprint does not match with a user."));
        }

        return tokenService.generateJWTToken(String.valueOf(user.get().getId()),
                String.valueOf(user.get().getUserType()),
                user.get().getId(),
                userTypeHelper.getUserTypeName(user.get().getUserType()));
    }

}
