package uom.mosip.attendanceservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.dto.auth.LoginRequestDTO;
import uom.mosip.attendanceservice.services.TokenService;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/admin/login")
    public Object login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        return tokenService.generateJWTToken(loginRequestDTO.getUsername(), "1");
    }
}
