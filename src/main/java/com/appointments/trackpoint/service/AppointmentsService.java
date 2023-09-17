package com.appointments.trackpoint.service;

import com.appointments.trackpoint.domain.Appointments;
import com.appointments.trackpoint.model.AppointmentsDTO;
import com.appointments.trackpoint.model.AuthUserDTO;
import com.appointments.trackpoint.model.PaginationResponse;
import com.appointments.trackpoint.model.PaginationResponseMeta;
import com.appointments.trackpoint.repos.AppointmentsRepository;
import com.appointments.trackpoint.repos.CustomerRepository;
import com.appointments.trackpoint.repos.DoctorRepository;
import com.appointments.trackpoint.repos.specification.AppointmentsSpecification;
import com.appointments.trackpoint.service.interfaces.IAppointmentsService;
import com.appointments.trackpoint.util.JwtUtility;
import com.appointments.trackpoint.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;


@Service
public class AppointmentsService implements IAppointmentsService {

    private final AppointmentsRepository appointmentsRepository;
    private final DoctorRepository doctorRepository;
    private final CustomerRepository customerRepository;
    private final JwtUtility jwtUtility;

    public AppointmentsService(final AppointmentsRepository appointmentsRepository,
                               final DoctorRepository doctorRepository, final CustomerRepository customerRepository, final JwtUtility jwtUtility) {
        this.appointmentsRepository = appointmentsRepository;
        this.doctorRepository = doctorRepository;
        this.customerRepository = customerRepository;
        this.jwtUtility = jwtUtility;
    }

    public List<AppointmentsDTO> findAll() {
        final List<Appointments> appointmentsList = appointmentsRepository.findAll(Sort.by("id"));
        return appointmentsList.stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    @Transactional
    public PaginationResponse<AppointmentsDTO> findAllByDoctorUsername(
            String token, int pageNumber, int pageSize,
            String descriptionFilter, String customerNameFilter,
            OffsetDateTime startDateFilter, OffsetDateTime endDateFilter,
            Boolean completedFilter, String sortBy, String sortDirectionString) {
        AuthUserDTO authUserDTO = jwtUtility.extractAuthUserDTO(token);


        Sort.Direction sortDirection;

        if (sortBy == null || sortDirectionString == null) {
            sortBy = "startDate";
            sortDirection = Sort.Direction.ASC;
        } else {
            if (sortDirectionString.equals("desc")) {
                sortDirection = Sort.Direction.DESC;
            } else {
                sortDirection = Sort.Direction.ASC;
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        System.out.println("descriptionFilter: " + descriptionFilter);
        System.out.println("customerNameFilter: " + customerNameFilter);
        System.out.println("startDateFilter: " + startDateFilter);
        System.out.println("endDateFilter: " + endDateFilter);
        System.out.println("completedFilter: " + completedFilter);


        Specification<Appointments> spec = Specification
                .where(AppointmentsSpecification.hasDoctorName(authUserDTO.getName()))
                .and(AppointmentsSpecification.hasDescription(descriptionFilter))
                .and(AppointmentsSpecification.hasCustomerName(customerNameFilter))
                .and(AppointmentsSpecification.startDateAfter(startDateFilter))
                .and(AppointmentsSpecification.endDateBefore(endDateFilter))
                .and(AppointmentsSpecification.isCompleted(completedFilter));

        /*Page<AppointmentsDTO> appointmentsList = appointmentsRepository
                .findAllByDoctorUsername(authUserDTO.getId(), pageable);*/

        // map to dto
        // create new appointmentsdto variable
        Page<Appointments> appointmentsList1 = appointmentsRepository.findAll(spec, pageable);

      /*  Page<AppointmentsDTO> appointmentsList1 = appointmentsRepository.findAll(spec, pageable).stream()
                .map(appointments -> mapToDTO(appointments)).toList();*/

        List<AppointmentsDTO> mappedAppointments = appointmentsList1.stream()
                .map(this::mapToDTO)
                .toList();

        PaginationResponse<AppointmentsDTO> paginationResponse = new PaginationResponse<>();
        PaginationResponseMeta pagResponseMeta = new PaginationResponseMeta();
        paginationResponse.setData(mappedAppointments);


        pagResponseMeta.setCurrentPage(pageNumber);
        pagResponseMeta.setItemsPerPage(pageSize);
        pagResponseMeta.setTotalItems(appointmentsList1.getTotalElements());
        pagResponseMeta.setTotalPages(appointmentsList1.getTotalPages());

        paginationResponse.setMeta(pagResponseMeta);
        return paginationResponse;
    }


    public AppointmentsDTO get(final Long id) {
        return appointmentsRepository.findById(id)
                .map(appointments -> mapToDTO(appointments))
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

    private AppointmentsDTO mapToDTO(final Appointments appointments) {
        final AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
        appointmentsDTO.setId(appointments.getId());
        appointmentsDTO.setDescription(appointments.getDescription());
        appointmentsDTO.setStartDate(appointments.getStartDate());
        appointmentsDTO.setEndDate(appointments.getEndDate());
        appointmentsDTO.setDoctor(appointments.getDoctor().getName());
        appointmentsDTO.setCustomer(appointments.getCustomer().getName());
        appointmentsDTO.setAppointment_completed(appointments.isAppointment_completed());
        return appointmentsDTO;
    }

    private Appointments mapToEntity(final AppointmentsDTO appointmentsDTO,
                                     final Appointments appointments) {
        appointments.setDescription(appointmentsDTO.getDescription());
        appointments.setStartDate(appointmentsDTO.getStartDate());
        appointments.setEndDate(appointmentsDTO.getEndDate());

/*        final Doctor doctor = appointmentsDTO.getDoctor() == null ? null : doctorRepository.findById(appointmentsDTO.getDoctor())
                .orElseThrow(() -> new NotFoundException("doctor not found"));

        appointments.setDoctor(doctor);

        final Customer customer = appointmentsDTO.getCustomer() == null ? null : customerRepository.findById(appointmentsDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));

        appointments.setCustomer(customer);*/

        return appointments;
    }


}
