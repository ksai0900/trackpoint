package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.AppUser;
import com.appointments.trackpoint.domain.Doctor;
import com.appointments.trackpoint.model.AuthUserDTO;
import com.appointments.trackpoint.repos.DoctorRepository;
import com.appointments.trackpoint.repos.AppUserRepository;
import com.appointments.trackpoint.util.JwtUtility;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private final DoctorRepository doctorRepository;
    private final AppUserRepository appUserRepository;

    private JwtUtility jwtUtility;

    public LoginService(
            final DoctorRepository doctorRepository,
            final AppUserRepository appUserRepository,
            final JwtUtility jwtUtility) {

        this.doctorRepository = doctorRepository;
        this.appUserRepository = appUserRepository;
        this.jwtUtility = jwtUtility;
    }

    @Transactional
    public AuthUserDTO login(
            final String username,
            final String password,
            final String role,
            final HttpServletResponse response) {

        AuthUserDTO authUserDTO = new AuthUserDTO();
        if(role.equals("doctor")){
            Doctor doctor = doctorRepository.findDoctorByUsernameAndPassword(username, password);
            if(doctor == null){
                return null;
            }
            authUserDTO.setId(doctor.getId());
            authUserDTO.setUsername(doctor.getAppUser().getUsername());
            authUserDTO.setName(doctor.getName());
            authUserDTO.setCategory("doctor");
            jwtUtility.generateTokenInCookie(authUserDTO, response);
            return authUserDTO;

        }
        else if(role.equals("secretary")){
            return null;
        }
        else{
            return null;
        }



    }


}
