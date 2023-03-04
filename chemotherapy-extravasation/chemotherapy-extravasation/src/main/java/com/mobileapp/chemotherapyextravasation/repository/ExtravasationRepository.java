package com.mobileapp.chemotherapyextravasation.repository;

import com.mobileapp.chemotherapyextravasation.entity.Extravasation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtravasationRepository extends JpaRepository<Extravasation, Long> {

    Optional<Extravasation> findByPatientId(Long patientId);

    long deleteByPatientId(Long patientId);
}
