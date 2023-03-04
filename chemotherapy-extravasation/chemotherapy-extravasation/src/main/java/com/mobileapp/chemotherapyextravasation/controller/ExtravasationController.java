package com.mobileapp.chemotherapyextravasation.controller;

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

import com.mobileapp.chemotherapyextravasation.dto.ExtravasationDto;
import com.mobileapp.chemotherapyextravasation.service.ExtravasationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/extravasation")
public class ExtravasationController {

    @Autowired
    private ExtravasationService extravasationService;

    // Get mappings for a patients extravasation
    @PreAuthorize("hasRole('NURSE')")
    @GetMapping("/{patientId}")
    public ResponseEntity<ExtravasationDto> getExtravasationByPatientId(@PathVariable Long patientId) {

        ExtravasationDto extravasation = extravasationService.getExtravasationByPatientId(patientId);
        return new ResponseEntity<ExtravasationDto>(extravasation, HttpStatus.OK);

    }

    // @PreAuthorize("hasRole('NURSE')")
    // @GetMapping("/{id}")
    // public ResponseEntity<ExtravasationDto> getExtravasationById(@PathVariable Long id) {

    //     ExtravasationDto extravasation = extravasationService.getExtravasationById(id);
    //     return new ResponseEntity<ExtravasationDto>(extravasation, HttpStatus.OK);

    // }

    // Post mappings for a patients extravasation
    @PreAuthorize("hasRole('NURSE')")
    @PostMapping("")
    public ResponseEntity<ExtravasationDto> createExtravasation(@RequestBody ExtravasationDto extravasation) {
        
        ExtravasationDto newExtravasation = extravasationService.createExtravasation(extravasation);
        return new ResponseEntity<>(newExtravasation, HttpStatus.CREATED);
        
    }

    // Put mappings for a patients extravasation
    @PreAuthorize("hasRole('NURSE')")
    @PutMapping("/{patientId}")
    public ResponseEntity<ExtravasationDto> updateExtravasationByPatientId(
                @PathVariable Long patientId, 
                @RequestBody ExtravasationDto extravasation) {

        extravasation.setPatientId(patientId);
        ExtravasationDto updatedExtravasation = extravasationService.updateExtravasationByPatientId(patientId, extravasation);
        return new ResponseEntity<>(updatedExtravasation, HttpStatus.OK);


    }

    // @PreAuthorize("hasRole('NURSE')")
    // @PutMapping("/{id}")
    // public ResponseEntity<ExtravasationDto> updateExtravasationById(
    //             @PathVariable Long id,
    //             @RequestBody ExtravasationDto extravasation) {

    //     ExtravasationDto updatedExtravasation = extravasationService.updateExtravasationById(id, extravasation);
    //     return new ResponseEntity<ExtravasationDto>(updatedExtravasation, HttpStatus.OK);

    // }

    // Delete mappings for a patients extravasation
    @PreAuthorize("hasRole('NURSE')")
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deleteExtravasationByPatientId(@PathVariable Long patientId) {

        extravasationService.deleteExtravasationByPatientId(patientId);
        return new ResponseEntity<String>("Deleted extravasation with patient id " + patientId + ".", HttpStatus.OK);
    
    }

    // @PreAuthorize("hasRole('NURSE')")
    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteExtravasationById(@PathVariable Long id) {

    //     extravasationService.deleteExtravasationById(id);
    //     return new ResponseEntity<String>("Deleted extravasation with id " + id + ".", HttpStatus.OK);

    // }
}
