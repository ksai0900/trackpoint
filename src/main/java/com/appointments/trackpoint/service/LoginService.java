package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.Doctor;
import com.appointments.trackpoint.domain.Secretary;
import com.appointments.trackpoint.model.AuthUserDTO;
import com.appointments.trackpoint.repos.AppUserRepository;
import com.appointments.trackpoint.repos.DoctorRepository;
import com.appointments.trackpoint.repos.SecretaryRepository;
import com.appointments.trackpoint.util.JwtUtility;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private final DoctorRepository doctorRepository;
    private final SecretaryRepository secretaryRepository;
    private final AppUserRepository appUserRepository;

    private JwtUtility jwtUtility;

    public LoginService(
            final SecretaryRepository secretaryRepository,
            final DoctorRepository doctorRepository,
            final AppUserRepository appUserRepository,
            final JwtUtility jwtUtility) {

        this.secretaryRepository = secretaryRepository;
        this.doctorRepository = doctorRepository;
        this.appUserRepository = appUserRepository;
        this.jwtUtility = jwtUtility;
    }



    @Transactional
    public AuthUserDTO login(
            final String username,
            final String password,
            final String role,
            final HttpServletResponse response) {    AuthUserDTO authUserDTO = new AuthUserDTO();

        Object user;

        if ("doctor".equals(role)) {
            user = doctorRepository.findDoctorByUsernameAndPassword(username, password);
        } else {
            user = secretaryRepository.findSecretaryByUsernameAndPassword(username, password);
        }

        if (user == null) {
            return null;
        }

        if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            authUserDTO.setId(doctor.getId());
            authUserDTO.setUsername(doctor.getAppUser().getUsername());
            authUserDTO.setName(doctor.getName());
        } else {
            Secretary secretary = (Secretary) user;
            // Assuming secretary has similar methods, adjust as necessary
            authUserDTO.setId(secretary.getId());
            authUserDTO.setUsername(secretary.getAppUser().getUsername());
            authUserDTO.setName(secretary.getName());
        }

        authUserDTO.setCategory(role);
        jwtUtility.generateTokenInCookie(authUserDTO, response);
        return authUserDTO;
    }
}
