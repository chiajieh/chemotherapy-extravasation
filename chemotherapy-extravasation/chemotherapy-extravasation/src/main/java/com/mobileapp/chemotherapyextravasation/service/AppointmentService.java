package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.AppointmentDto;
import jakarta.validation.Valid;

import java.util.List;

public interface AppointmentService {
    public List<AppointmentDto> getPatientAppointmentsByPatientId(Long patientId);
    public List<AppointmentDto> createAppointments(AppointmentDto appointmentDto);
    public AppointmentDto updateAppointmentById(Long appointmentId, @Valid AppointmentDto appointmentDto);
    public void deleteAppointmentById(Long appointmentId);
}
