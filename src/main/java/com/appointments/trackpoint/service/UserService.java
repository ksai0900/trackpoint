package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.AppUser;
import com.appointments.trackpoint.model.AppUserDTO;
import com.appointments.trackpoint.repos.AppUserRepository;
import com.appointments.trackpoint.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final AppUserRepository appUserRepository;

    public UserService(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUserDTO> findAll() {
        final List<AppUser> appUsers = appUserRepository.findAll(Sort.by("id"));
        return appUsers.stream()
                .map(user -> mapToDTO(user, new AppUserDTO()))
                .toList();
    }

    public AppUserDTO get(final Long id) {
        return appUserRepository.findById(id)
                .map(user -> mapToDTO(user, new AppUserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AppUserDTO appUserDTO) {
        final AppUser appUser = new AppUser();
        mapToEntity(appUserDTO, appUser);
        return appUserRepository.save(appUser).getId();
    }

    public void update(final Long id, final AppUserDTO appUserDTO) {
        final AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(appUserDTO, appUser);
        appUserRepository.save(appUser);
    }

    public void delete(final Long id) {
        appUserRepository.deleteById(id);
    }

    private AppUserDTO mapToDTO(final AppUser appUser, final AppUserDTO appUserDTO) {
        appUserDTO.setId(appUser.getId());
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setPassword(appUser.getPassword());
        return appUserDTO;
    }

    private AppUser mapToEntity(final AppUserDTO appUserDTO, final AppUser appUser) {
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setPassword(appUserDTO.getPassword());
        return appUser;
    }

}
