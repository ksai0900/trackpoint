package com.appointments.trackpoint.controller;

import com.appointments.trackpoint.model.AppointmentsDTO;
import com.appointments.trackpoint.model.NewAppointmentDTO;
import com.appointments.trackpoint.model.PaginationResponse;
import com.appointments.trackpoint.service.AppointmentsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AppointmentsController {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(AppointmentsController.class);
    private final AppointmentsService appointmentsService;

    public AppointmentsController(final AppointmentsService appointmentsService) {
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
            @RequestBody  NewAppointmentDTO appointment,
            @RequestParam(required = false) String newCustomerName,
            @RequestParam(required = false) Long existingCustomer) {
        final Long createdId = appointmentsService.create(appointment, newCustomerName, existingCustomer);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }



/*    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAppointments(@PathVariable(name = "id") final Long id,
                                                   @RequestBody @Valid final NewAppointmentDTO newAppointmentDTO) {
        appointmentsService.update(id, newAppointmentDTO);
        return ResponseEntity.ok(id);
    }*/

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAppointments(@PathVariable(name = "id") final Long id) {
        appointmentsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> completeAppointment(@PathVariable(name = "id") final Long id) {
        appointmentsService.complete(id);
        return ResponseEntity.noContent().build();
    }

}
