package com.appointments.trackpoint.repos;

import com.appointments.trackpoint.domain.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
    @Query(value = "SELECT s.* FROM app_user u INNER JOIN secretary s on s.user_id = u.id " +
            "WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Secretary findSecretaryByUsernameAndPassword(String username, String password);
}