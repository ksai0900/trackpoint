package com.appointments.trackpoint.rest;

import com.appointments.trackpoint.model.AppointmentsDTO;
import com.appointments.trackpoint.model.PaginationResponse;
import com.appointments.trackpoint.service.AppointmentsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AppointmentsResource {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(AppointmentsResource.class);
    private final AppointmentsService appointmentsService;

    public AppointmentsResource(final AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentsDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentsService.findAll());
    }

    // create a new endpoint to get all appointments of a specific doctor
    @GetMapping("/doctor")
    public ResponseEntity<PaginationResponse<AppointmentsDTO>> getAllAppointmentsByDoctorId(
            @CookieValue("TOKEN") String token,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(required = false) String descriptionFilter,
            @RequestParam(required = false) String customerNameFilter,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDateFilter,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDateFilter,
            @RequestParam(required = false) Boolean completedFilter,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortDirection) {

        LOGGER.info("getAllAppointmentsByDoctorId : " + token);
        LOGGER.info("sortBy : " + sortField);
        LOGGER.info("sortDirection : " + sortDirection);
        return ResponseEntity.ok(appointmentsService.findAllByDoctorUsername(
                token,
                pageNumber,
                pageSize,
                descriptionFilter,
                customerNameFilter,
                startDateFilter,
                endDateFilter,
                completedFilter,
                sortField,
                sortDirection));
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
