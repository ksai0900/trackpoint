package com.appointments.trackpoint.repos;

import com.appointments.trackpoint.domain.Appointments;
import com.appointments.trackpoint.model.AppointmentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AppointmentsRepository extends JpaRepository<Appointments, Long>, JpaSpecificationExecutor<Appointments> {

/*    @Query(value = "SELECT a.id, a.description, a.start_date, a.end_date, c.name as customer, d.name as doctor, a.appointment_completed from appointments as a " +
            "INNER JOIN doctor as d on d.id = a.doctor_id " +
            "INNER JOIN customer as c on c.id = a.customer_id " +
            "WHERE a.doctor_id = ?1", nativeQuery = true)
    Page<AppointmentsDTO> findAllByDoctorUsername(Long doctorId, Pageable pageable);*/

    @Query("SELECT new com.appointments.trackpoint.model.AppointmentsDTO(a.id, a.description, a.startDate, a.endDate, d.name, c.name, a.appointment_completed) " +
            "FROM Appointments a " +
            "JOIN a.doctor d " +
            "JOIN a.customer c " +
            "WHERE d.id = :doctorId")
    Page<AppointmentsDTO> findAllByDoctorUsername(@Param("doctorId") Long doctorId, Pageable pageable);

/*    @Query(value = "SELECT sub.description, sub.start_date, sub.end_date, sub.customer_name, sub.doctor_name, sub.appointment_completed " +
            "FROM ( " +
            "SELECT a.doctor_id, a.customer_id, a.description, a.start_date, a.end_date, c.name as customer_name, d.name as doctor_name, a.appointment_completed " +
            "FROM appointments as a " +
            "INNER JOIN doctor as d on d.id = a.doctor_id " +
            "INNER JOIN customer as c on c.id = a.customer_id " +
            "WHERE a.doctor_id = ?1" +
            ") as sub", nativeQuery = true)
    Page<Appointments> findAllByDoctorUsername(Long doctorId, Pageable pageable);*/
}
