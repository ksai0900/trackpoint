package com.appointments.trackpoint.rest;

import com.appointments.trackpoint.model.SecretaryDTO;
import com.appointments.trackpoint.service.SecretaryService;
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
@RequestMapping(value = "/api/secretarys", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecretaryResource {

    private final SecretaryService secretaryService;

    public SecretaryResource(final SecretaryService secretaryService) {
        this.secretaryService = secretaryService;
    }

    @GetMapping
    public ResponseEntity<List<SecretaryDTO>> getAllSecretarys() {
        return ResponseEntity.ok(secretaryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryDTO> getSecretary(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(secretaryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSecretary(
            @RequestBody @Valid final SecretaryDTO secretaryDTO) {
        final Long createdId = secretaryService.create(secretaryDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSecretary(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SecretaryDTO secretaryDTO) {
        secretaryService.update(id, secretaryDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSecretary(@PathVariable(name = "id") final Long id) {
        secretaryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
