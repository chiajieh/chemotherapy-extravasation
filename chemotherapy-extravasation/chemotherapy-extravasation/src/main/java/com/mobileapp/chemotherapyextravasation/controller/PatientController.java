package com.mobileapp.chemotherapyextravasation.controller;

import com.mobileapp.chemotherapyextravasation.dto.PatientDto;
import com.mobileapp.chemotherapyextravasation.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/patients")
public class PatientController {
    private PatientService patientService;

    /**
     * build create Patient REST API
     * @param patient
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patient) {
        PatientDto savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    /**
     * build get patient by id REST API
     *  http://localhost:8080/api/patients/1
     * @param PatientId
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") long PatientId) {
        PatientDto patient = patientService.getPatientById(PatientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    /**
     * Get patient details by email
     * -http://localhost:8080/api/patients/details?email=manyjjohn@gmail.com
     * @param email
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @GetMapping("details")
    public ResponseEntity<PatientDto> getChemotherapyDetailsByEmail(@RequestParam String email) {
        List<PatientDto> patientList = patientService.getAllPatients();
        for (PatientDto patient : patientList) {
            if (patient.getEmail().equals(email)) {
                return new ResponseEntity<>(patient, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Get patient details by username
     * -http://localhost:8080/api/patients/info?username=manyj
     * @param username
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @GetMapping("info")
    public ResponseEntity<PatientDto> getChemotherapyDetailsByUsername(@RequestParam String username) {
        List<PatientDto> patientList = patientService.getAllPatients();
        for (PatientDto patient : patientList) {
            if (patient.getUsername().equals(username)) {
                return new ResponseEntity<>(patient, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Build Get All Patients REST API
     * http://localhost:8080/api/patients
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatient() {
        List<PatientDto> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    /**
     * Build Update Patient REST API
     * http://localhost:8080/api/patients/1
     * @param patientId
     * @param patient
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @PutMapping("{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("id") long patientId,
                                                    @RequestBody @Valid PatientDto patient) {
        patient.setId(patientId);
        PatientDto updatedPatient = patientService.updatePatient(patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    /**
     * Build delete Patient REST API
     * @param patientId
     * @return
     */
    @PreAuthorize("hasRole('NURSE')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") long patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>("Patient with id " +patientId +" successfully deleted", HttpStatus.OK);
    }
}
