package com.appointments.trackpoint.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppUserDTO {

    private Long id;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String password;

}
