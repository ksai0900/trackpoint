package com.appointments.trackpoint.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAppointmentDTO {

    private String description;
    private Long doctor;
    private Long duration;
    private OffsetDateTime startDate;

}
