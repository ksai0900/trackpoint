package com.appointments.trackpoint.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SecretaryDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Long appUser;

}
