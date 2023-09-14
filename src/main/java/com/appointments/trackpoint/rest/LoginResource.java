package com.appointments.trackpoint.rest;

import com.appointments.trackpoint.domain.AppUser;
import com.appointments.trackpoint.model.AuthUserDTO;
import com.appointments.trackpoint.model.LoginDTOApp;
import com.appointments.trackpoint.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class LoginResource {

    private final LoginService loginService;

    public LoginResource(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserDTO> login(@RequestBody final LoginDTOApp loginDTO, HttpServletResponse response) {
        AuthUserDTO appUser = loginService.login(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getRole(), response);
        if (appUser != null) {
            return ResponseEntity.ok().body(appUser);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
