package com.mobileapp.chemotherapyextravasation.repository;

import com.mobileapp.chemotherapyextravasation.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public List<Appointment> findByPatientId(Long patientId);
}