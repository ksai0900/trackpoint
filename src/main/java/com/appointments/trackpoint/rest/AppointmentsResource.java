package com.appointments.trackpoint.rest;

import com.appointments.trackpoint.model.AppointmentsDTO;
import com.appointments.trackpoint.service.AppointmentsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/appointmentss", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentsResource {

    private final AppointmentsService appointmentsService;

    public AppointmentsResource(final AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentsDTO>> getAllAppointmentss() {
        return ResponseEntity.ok(appointmentsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentsDTO> getAppointments(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(appointmentsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAppointments(
            @RequestBody @Valid final AppointmentsDTO appointmentsDTO) {
        final Long createdId = appointmentsService.create(appointmentsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAppointments(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final AppointmentsDTO appointmentsDTO) {
        appointmentsService.update(id, appointmentsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAppointments(@PathVariable(name = "id") final Long id) {
        appointmentsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
