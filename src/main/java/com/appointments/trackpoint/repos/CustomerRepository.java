package com.appointments.trackpoint.repos;

import com.appointments.trackpoint.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
