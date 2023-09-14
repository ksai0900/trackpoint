package com.appointments.trackpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTOApp extends AppUserDTO {
    @JsonIgnore
    private Long id;
    private String role;

}
