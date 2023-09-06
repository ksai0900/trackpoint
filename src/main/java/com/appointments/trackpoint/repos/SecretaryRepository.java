package com.appointments.trackpoint.repos;

import com.appointments.trackpoint.domain.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
}
