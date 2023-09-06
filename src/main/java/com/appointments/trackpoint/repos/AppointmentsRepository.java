package com.appointments.trackpoint.repos;

import com.appointments.trackpoint.domain.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
}
