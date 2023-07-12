package uom.mosip.attendanceservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        // return tokenService.decodeJWTToken(loginRequestDTO.getUsername());
    }

    @GetMapping("/pend")
    public String pend() {
        return "This is a protected resource.";
    }
}
