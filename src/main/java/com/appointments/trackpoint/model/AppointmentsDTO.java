package com.appointments.trackpoint.model;

import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppointmentsDTO {

    private Long id;

    @Size(max = 255)
    private String description;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private Long doctor;

    private Long customer;
    private boolean confirmed;

}
