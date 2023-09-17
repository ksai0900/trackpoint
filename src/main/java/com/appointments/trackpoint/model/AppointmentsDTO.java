package com.appointments.trackpoint.model;

import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsDTO {

    private Long id;

    @Size(max = 255)
    private String description;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private String doctor;

    private String customer;
    private boolean appointment_completed;

}
