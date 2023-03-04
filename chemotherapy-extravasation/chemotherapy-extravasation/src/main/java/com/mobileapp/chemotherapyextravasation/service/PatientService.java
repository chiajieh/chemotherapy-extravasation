package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.PatientDto;

import java.util.List;

public interface PatientService {
    public PatientDto createPatient(PatientDto patientDto);

    public PatientDto getPatientById(long patientId);

    public List<PatientDto> getAllPatients();

    public PatientDto updatePatient(PatientDto patient);

    public void deletePatient(long patientId);
}
