package com.mobileapp.chemotherapyextravasation.controller;


import com.mobileapp.chemotherapyextravasation.dto.NurseDto;
import com.mobileapp.chemotherapyextravasation.service.NurseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/nurse")
public class NurseController {

    private NurseService nurseService;

    //build create Nurse Details REST API
    @PostMapping
    public ResponseEntity<NurseDto> createNurse(@Valid @RequestBody NurseDto nurse) {
        NurseDto savedNurse = nurseService.createNurse(nurse);
        return new ResponseEntity<>(savedNurse, HttpStatus.CREATED);
    }

    //build get nurse by id REST API
    //http://localhost:8080/api/nurse/1
    @GetMapping("{id}")
    public ResponseEntity<NurseDto> getNurseById(@PathVariable("id") long NurseId) {
        NurseDto nurse = nurseService.getNurseById(NurseId);
        return new ResponseEntity<>(nurse, HttpStatus.OK);
    }

    //Build Get All nurses REST API
    //http://localhost:8080/api/nurse
    @GetMapping
    public ResponseEntity<List<NurseDto>> getAllNurse() {
        List<NurseDto> nurses = nurseService.getAllNurse();
        return new ResponseEntity<>(nurses, HttpStatus.OK);
    }

    //Build Update nurse REST API
    //http://localhost:8080/api/nurse/1
    @PutMapping("{id}")
    public ResponseEntity<NurseDto> updateNurse(@PathVariable("id") long nurseId,
                                                @RequestBody @Valid NurseDto nurse) {
        nurse.setId(nurseId);
        NurseDto updatedNurse = nurseService.updateNurse(nurse);
        return new ResponseEntity<>(updatedNurse, HttpStatus.OK);
    }

    //Build delete nurse REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable("id") long nurseId) {
        nurseService.deleteNurse(nurseId);
        return new ResponseEntity<>("Nurse with id " + nurseId +" successfully deleted", HttpStatus.OK);
    }
}

