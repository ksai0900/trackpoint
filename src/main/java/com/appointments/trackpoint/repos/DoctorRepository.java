package com.appointments.trackpoint.repos;

import com.appointments.trackpoint.domain.AppUser;
import com.appointments.trackpoint.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
/*    SELECT * FROM app_user u INNER JOIN doctor d on d.user_id = u.id WHERE username = ?1 AND password = ?2*/


    @Query(value = "SELECT d.* FROM app_user u INNER JOIN doctor d on d.user_id = u.id " +
            "WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Doctor findDoctorByUsernameAndPassword(String username, String password);
}
