package com.appointments.trackpoint.service.interfaces;

import com.appointments.trackpoint.model.AppointmentsDTO;
import com.appointments.trackpoint.model.PaginationResponse;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;

public interface IAppointmentsService {


    PaginationResponse<AppointmentsDTO> findAllByDoctorUsername(
            String doctor,
            int pageNumber,
            int pageSize,
            String descriptionFilter,
            String customerNameFilter,
            OffsetDateTime startDateFilter,
            OffsetDateTime endDateFilter,
            Boolean completedFilter,
            String sortBy,
            String sortDirection
            );

   /* PaginationResponse<AppointmentsDTO> findAllByDoctorUsername(String doctor, int pageNumber, int pageSize);*/

}
