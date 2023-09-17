package com.appointments.trackpoint.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {

    private Long id;
    private String username;
    private String name;
    private String category;
}
