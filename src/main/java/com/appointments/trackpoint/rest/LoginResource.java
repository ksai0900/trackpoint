package com.appointments.trackpoint.rest;

import com.appointments.trackpoint.model.LoginDTO;
import com.appointments.trackpoint.service.LoginService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginResource {

    private final LoginService loginService;

    public LoginResource(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody final LoginDTO loginDTO) {
        return ResponseEntity.ok(loginService.login(loginDTO.getUsername(), loginDTO.getPassword()));
    }
}
