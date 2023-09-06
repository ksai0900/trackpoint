package com.appointments.trackpoint.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DoctorDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String specialty;

    private Long secretary;

    private Long user;

}
