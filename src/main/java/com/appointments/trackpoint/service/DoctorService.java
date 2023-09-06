package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.Doctor;
import com.appointments.trackpoint.domain.Secretary;
import com.appointments.trackpoint.domain.User;
import com.appointments.trackpoint.model.DoctorDTO;
import com.appointments.trackpoint.repos.DoctorRepository;
import com.appointments.trackpoint.repos.SecretaryRepository;
import com.appointments.trackpoint.repos.UserRepository;
import com.appointments.trackpoint.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final SecretaryRepository secretaryRepository;
    private final UserRepository userRepository;

    public DoctorService(final DoctorRepository doctorRepository,
            final SecretaryRepository secretaryRepository, final UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.secretaryRepository = secretaryRepository;
        this.userRepository = userRepository;
    }

    public List<DoctorDTO> findAll() {
        final List<Doctor> doctors = doctorRepository.findAll(Sort.by("id"));
        return doctors.stream()
                .map(doctor -> mapToDTO(doctor, new DoctorDTO()))
                .toList();
    }

    public DoctorDTO get(final Long id) {
        return doctorRepository.findById(id)
                .map(doctor -> mapToDTO(doctor, new DoctorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DoctorDTO doctorDTO) {
        final Doctor doctor = new Doctor();
        mapToEntity(doctorDTO, doctor);
        return doctorRepository.save(doctor).getId();
    }

    public void update(final Long id, final DoctorDTO doctorDTO) {
        final Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }

    public void delete(final Long id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO mapToDTO(final Doctor doctor, final DoctorDTO doctorDTO) {
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSpecialty(doctor.getSpecialty());
        doctorDTO.setSecretary(doctor.getSecretary() == null ? null : doctor.getSecretary().getId());
        doctorDTO.setUser(doctor.getUser() == null ? null : doctor.getUser().getId());
        return doctorDTO;
    }

    private Doctor mapToEntity(final DoctorDTO doctorDTO, final Doctor doctor) {
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        final Secretary secretary = doctorDTO.getSecretary() == null ? null : secretaryRepository.findById(doctorDTO.getSecretary())
                .orElseThrow(() -> new NotFoundException("secretary not found"));
        doctor.setSecretary(secretary);
        final User user = doctorDTO.getUser() == null ? null : userRepository.findById(doctorDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        doctor.setUser(user);
        return doctor;
    }

}
