package com.mobileapp.chemotherapyextravasation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.chemotherapyextravasation.dto.AppointmentDto;
import com.mobileapp.chemotherapyextravasation.service.AppointmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    // Get mapping for a patients appointment's by patient id
    @PreAuthorize("hasAnyRole('NURSE','PATIENT')")
    @GetMapping("/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getPatientAppointmentsByPatientId(@PathVariable Long patientId) {

        List<AppointmentDto> appointments = appointmentService.getPatientAppointmentsByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Post mapping to create 7 follow up appointments. The first after 24 hours then every 2 weeks
    @PreAuthorize("hasAnyRole('NURSE')")
    @PostMapping("/")
    public ResponseEntity<List<AppointmentDto>> createAppointments(@RequestBody @Valid AppointmentDto appointmentDto) {

        List<AppointmentDto> appointments = appointmentService.createAppointments(appointmentDto);
        return new ResponseEntity<>(appointments, HttpStatus.CREATED);
    }

    // Put mapping for a appointment by id
    @PreAuthorize("hasAnyRole('NURSE','PATIENT')")
    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> updateAppointmentById(
                @PathVariable Long appointmentId, 
                @RequestBody @Valid AppointmentDto appointmentDto) {

        AppointmentDto appointment = appointmentService.updateAppointmentById(appointmentId, appointmentDto);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    // Delete mapping for a appointment by id
    @PreAuthorize("hasAnyRole('NURSE','PATIENT')")
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable Long appointmentId) {

        appointmentService.deleteAppointmentById(appointmentId);
        return new ResponseEntity<>("Deleted appointment with id " + appointmentId + ".", HttpStatus.OK);
    }
}
