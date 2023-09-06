package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.Appointments;
import com.appointments.trackpoint.domain.Customer;
import com.appointments.trackpoint.domain.Doctor;
import com.appointments.trackpoint.model.AppointmentsDTO;
import com.appointments.trackpoint.repos.AppointmentsRepository;
import com.appointments.trackpoint.repos.CustomerRepository;
import com.appointments.trackpoint.repos.DoctorRepository;
import com.appointments.trackpoint.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AppointmentsService {

    private final AppointmentsRepository appointmentsRepository;
    private final DoctorRepository doctorRepository;
    private final CustomerRepository customerRepository;

    public AppointmentsService(final AppointmentsRepository appointmentsRepository,
            final DoctorRepository doctorRepository, final CustomerRepository customerRepository) {
        this.appointmentsRepository = appointmentsRepository;
        this.doctorRepository = doctorRepository;
        this.customerRepository = customerRepository;
    }

    public List<AppointmentsDTO> findAll() {
        final List<Appointments> appointmentss = appointmentsRepository.findAll(Sort.by("id"));
        return appointmentss.stream()
                .map(appointments -> mapToDTO(appointments, new AppointmentsDTO()))
                .toList();
    }

    public AppointmentsDTO get(final Long id) {
        return appointmentsRepository.findById(id)
                .map(appointments -> mapToDTO(appointments, new AppointmentsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AppointmentsDTO appointmentsDTO) {
        final Appointments appointments = new Appointments();
        mapToEntity(appointmentsDTO, appointments);
        return appointmentsRepository.save(appointments).getId();
    }

    public void update(final Long id, final AppointmentsDTO appointmentsDTO) {
        final Appointments appointments = appointmentsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(appointmentsDTO, appointments);
        appointmentsRepository.save(appointments);
    }

    public void delete(final Long id) {
        appointmentsRepository.deleteById(id);
    }

    private AppointmentsDTO mapToDTO(final Appointments appointments,
            final AppointmentsDTO appointmentsDTO) {
        appointmentsDTO.setId(appointments.getId());
        appointmentsDTO.setDescription(appointments.getDescription());
        appointmentsDTO.setStartDate(appointments.getStartDate());
        appointmentsDTO.setEndDate(appointments.getEndDate());
        appointmentsDTO.setDoctor(appointments.getDoctor() == null ? null : appointments.getDoctor().getId());
        appointmentsDTO.setCustomer(appointments.getCustomer() == null ? null : appointments.getCustomer().getId());
        return appointmentsDTO;
    }

    private Appointments mapToEntity(final AppointmentsDTO appointmentsDTO,
            final Appointments appointments) {
        appointments.setDescription(appointmentsDTO.getDescription());
        appointments.setStartDate(appointmentsDTO.getStartDate());
        appointments.setEndDate(appointmentsDTO.getEndDate());
        final Doctor doctor = appointmentsDTO.getDoctor() == null ? null : doctorRepository.findById(appointmentsDTO.getDoctor())
                .orElseThrow(() -> new NotFoundException("doctor not found"));
        appointments.setDoctor(doctor);
        final Customer customer = appointmentsDTO.getCustomer() == null ? null : customerRepository.findById(appointmentsDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        appointments.setCustomer(customer);
        return appointments;
    }

}
