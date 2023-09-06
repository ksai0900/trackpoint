package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.User;
import com.appointments.trackpoint.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }


}
