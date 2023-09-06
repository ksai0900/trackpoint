package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.Secretary;
import com.appointments.trackpoint.domain.User;
import com.appointments.trackpoint.model.SecretaryDTO;
import com.appointments.trackpoint.repos.SecretaryRepository;
import com.appointments.trackpoint.repos.UserRepository;
import com.appointments.trackpoint.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;
    private final UserRepository userRepository;

    public SecretaryService(final SecretaryRepository secretaryRepository,
            final UserRepository userRepository) {
        this.secretaryRepository = secretaryRepository;
        this.userRepository = userRepository;
    }

    public List<SecretaryDTO> findAll() {
        final List<Secretary> secretarys = secretaryRepository.findAll(Sort.by("id"));
        return secretarys.stream()
                .map(secretary -> mapToDTO(secretary, new SecretaryDTO()))
                .toList();
    }

    public SecretaryDTO get(final Long id) {
        return secretaryRepository.findById(id)
                .map(secretary -> mapToDTO(secretary, new SecretaryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SecretaryDTO secretaryDTO) {
        final Secretary secretary = new Secretary();
        mapToEntity(secretaryDTO, secretary);
        return secretaryRepository.save(secretary).getId();
    }

    public void update(final Long id, final SecretaryDTO secretaryDTO) {
        final Secretary secretary = secretaryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(secretaryDTO, secretary);
        secretaryRepository.save(secretary);
    }

    public void delete(final Long id) {
        secretaryRepository.deleteById(id);
    }

    private SecretaryDTO mapToDTO(final Secretary secretary, final SecretaryDTO secretaryDTO) {
        secretaryDTO.setId(secretary.getId());
        secretaryDTO.setName(secretary.getName());
        secretaryDTO.setUser(secretary.getUser() == null ? null : secretary.getUser().getId());
        return secretaryDTO;
    }

    private Secretary mapToEntity(final SecretaryDTO secretaryDTO, final Secretary secretary) {
        secretary.setName(secretaryDTO.getName());
        final User user = secretaryDTO.getUser() == null ? null : userRepository.findById(secretaryDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        secretary.setUser(user);
        return secretary;
    }

}
