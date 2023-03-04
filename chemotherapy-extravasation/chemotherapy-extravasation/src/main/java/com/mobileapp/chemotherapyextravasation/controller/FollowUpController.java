package com.mobileapp.chemotherapyextravasation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.chemotherapyextravasation.dto.FollowUpDto;
import com.mobileapp.chemotherapyextravasation.service.FollowUpService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/followup")
public class FollowUpController {
    
    @Autowired
    FollowUpService followUpService;

    // Get mapping to get a follow up by apointment id
    @PreAuthorize("hasAnyRole('NURSE','PATIENT')")
    @GetMapping("/{appointmentId}")
    public ResponseEntity<FollowUpDto> getFollowUpByAppointmentId(@PathVariable Long appointmentId) {
        FollowUpDto followUpDto = followUpService.getFollowUpByAppointmentId(appointmentId);
        return new ResponseEntity<>(followUpDto, HttpStatus.OK);
    }

    // Post mapping to create a follow up for an appointment id
    @PreAuthorize("hasAnyRole('NURSE','PATIENT')")
    @PostMapping("/")
    public ResponseEntity<FollowUpDto> createFollowUpByAppointmentId(@RequestBody @Valid FollowUpDto followUpDto) {

        FollowUpDto newFollowUp = followUpService.createFollowUpByAppointmentId(followUpDto);
        return new ResponseEntity<>(newFollowUp, HttpStatus.CREATED);
    }

    // Put mapping to update a follow up by appointment id for patient
    @PreAuthorize("hasAnyRole('PATIENT')")
    @PutMapping("/patient/{appointmentId}")
    public ResponseEntity<FollowUpDto> updatePatientFollowUpByAppointmentId(@PathVariable Long appointmentId, 
                @RequestBody FollowUpDto followUpDto) {
        
        FollowUpDto updatedFollowUp = followUpService.updatePatientFollowUpByAppointmentId(appointmentId, followUpDto);
        return new ResponseEntity<>(updatedFollowUp, HttpStatus.OK);
    }

    // Put mapping to update a follow up by appointment id for nurse
    @PreAuthorize("hasAnyRole('NURSE')")
    @PutMapping("/nurse/{appointmentId}")
    public ResponseEntity<FollowUpDto> updateNurseFollowUpByAppointmentId(@PathVariable Long appointmentId, 
                @RequestBody FollowUpDto followUpDto) {
        
        FollowUpDto updatedFollowUp = followUpService.updateNurseFollowUpByAppointmentId(appointmentId, followUpDto);
        return new ResponseEntity<>(updatedFollowUp, HttpStatus.OK);
    }

    // Delete mapping to delete a follow up by appointment id
}
