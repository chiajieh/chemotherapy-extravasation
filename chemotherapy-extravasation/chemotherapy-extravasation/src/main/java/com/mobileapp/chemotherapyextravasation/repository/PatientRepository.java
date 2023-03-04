package com.mobileapp.chemotherapyextravasation.repository;

import com.mobileapp.chemotherapyextravasation.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByUsernameOrEmail(String username, String email);

    Optional<Patient> findByUsername(String username);

    //Checks whether username exists
    Boolean existsByUsername(String username);

    //Checks whether email exists
    Boolean existsByEmail(String email);


}