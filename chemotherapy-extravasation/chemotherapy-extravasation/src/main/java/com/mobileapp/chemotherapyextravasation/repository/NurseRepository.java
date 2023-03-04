package com.mobileapp.chemotherapyextravasation.repository;

import com.mobileapp.chemotherapyextravasation.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Optional<Nurse> findByEmail(String email);

    Optional<Nurse> findByUsernameOrEmail(String username, String email);

    Optional<Nurse> findByUsername(String username);

    //Checks whether username exists
    Boolean existsByUsername(String username);

    //Checks whether email exists
    Boolean existsByEmail(String email);

}