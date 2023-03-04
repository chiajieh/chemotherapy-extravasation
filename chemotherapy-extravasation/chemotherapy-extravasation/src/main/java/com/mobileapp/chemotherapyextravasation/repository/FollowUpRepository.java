package com.mobileapp.chemotherapyextravasation.repository;

import com.mobileapp.chemotherapyextravasation.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    public Optional<FollowUp> findByAppointmentId(Long appointmentId);
}
