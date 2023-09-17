package com.appointments.trackpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO extends AppUserDTO {
    @JsonIgnore
    private Long id;
    private String role;

}
