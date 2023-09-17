package com.appointments.trackpoint.repos.specification;

import com.appointments.trackpoint.domain.Appointments;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

public class AppointmentsSpecification {

    public static Specification<Appointments> hasDoctorName(String doctorName) {
        return (appointment, cq, cb) -> doctorName == null ? null : cb.like(cb.lower(appointment.join("doctor").get("name")), "%" + doctorName.toLowerCase() + "%");
    }

    public static Specification<Appointments> hasDescription(String description) {
        return (appointment, cq, cb) -> description == null ? null : cb.like(cb.lower(appointment.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Appointments> hasCustomerName(String customerName) {
        return (appointment, cq, cb) -> customerName == null ? null : cb.like(cb.lower(appointment.join("customer").get("name")), "%" + customerName.toLowerCase() + "%");
    }
    public static Specification<Appointments> startDateAfter(OffsetDateTime startDate) {
        return (appointment, cq, cb) -> startDate == null ? null : cb.greaterThanOrEqualTo(appointment.get("startDate"), startDate);
    }

    public static Specification<Appointments> endDateBefore(OffsetDateTime endDate) {
        return (appointment, cq, cb) -> endDate == null ? null : cb.lessThanOrEqualTo(appointment.get("endDate"), endDate);
    }

    public static Specification<Appointments> isCompleted(Boolean completed) {
        return (appointment, cq, cb) -> completed == null ? null : cb.equal(appointment.get("appointment_completed"), completed);
    }
}
