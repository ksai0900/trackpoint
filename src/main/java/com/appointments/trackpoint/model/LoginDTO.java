package com.appointments.trackpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO extends UserDTO {
    @JsonIgnore
    private Long id;

}
