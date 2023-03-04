package com.mobileapp.chemotherapyextravasation.service.implementation;

import com.mobileapp.chemotherapyextravasation.dto.PatientDto;
import com.mobileapp.chemotherapyextravasation.entity.Patient;
import com.mobileapp.chemotherapyextravasation.exception.EmailAlreadyExistsException;
import com.mobileapp.chemotherapyextravasation.exception.ResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.repository.PatientRepository;
import com.mobileapp.chemotherapyextravasation.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    /**
     * Method to create a new patient entity
     * @param patientDto
     * @return
     */
    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        Optional<Patient> optionalPatient = patientRepository.findByEmail(patientDto.getEmail());

        if(optionalPatient.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for Patient");
        }
        Patient savedPatient = patientRepository.save(patient);
        PatientDto savedPatientDto = modelMapper.map(savedPatient, PatientDto.class);
        return savedPatientDto;
    }

    /**
     * Method to get a patient's details by ID
     * @param patientId
     * @return
     */
    @Override
    public PatientDto getPatientById(long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
        return modelMapper.map(patient, PatientDto.class);
    }

    /**
     * Method to get all the patients
     * @return
     */
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((patient) -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to update a patient's details
     * @param patient
     * @return
     */
    @Override
    public PatientDto updatePatient(PatientDto patient) {
        Patient existingPatient = patientRepository.findById(patient.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patient.getId()));
        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setUsername(patient.getUsername());
        existingPatient.setEmail(patient.getEmail());
//        existingPatient.setNurse(patient.getNurse());
//        existingPatient.setAppointments(patient.getAppointments());
//        existingPatient.setExtravasation(patient.getExtravasation());
        Patient updatedPatient = patientRepository.save(existingPatient);
        return modelMapper.map(updatedPatient, PatientDto.class);


    }

    /**
     * Method to delete a patient entity
     * @param patientId
     */
    @Override
    public void deletePatient(long patientId) {
        Patient existingPatient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "id", patientId));
        patientRepository.deleteById(patientId);
    }
}
