package com.mobileapp.chemotherapyextravasation.repository;

import com.mobileapp.chemotherapyextravasation.entity.Chemotherapy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChemotherapyRepository extends JpaRepository<Chemotherapy, Long> {
    Optional<Chemotherapy> findByName(String name);
}