package com.mobileapp.chemotherapyextravasation.controller;

import com.mobileapp.chemotherapyextravasation.dto.ChemotherapyDto;
import com.mobileapp.chemotherapyextravasation.service.ChemotherapyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/chemotherapy")
public class ChemotherapyController {
    private ChemotherapyService chemotherapyService;

    /**
     * Get Chemotherapy details by name
     * -http://localhost:8080/api/chemotherapy/details?name=DAUNOrubicin
     * @param name
     * @return
     */
    @GetMapping("details")
    public ResponseEntity<ChemotherapyDto> getChemotherapyDetailsByName(@RequestParam String name) {
        List<ChemotherapyDto> chemotherapyList = chemotherapyService.getAllChemotherapyInfo();
        for (ChemotherapyDto chemotherapy : chemotherapyList) {
            if (chemotherapy.getName().equals(name)) {
                return new ResponseEntity<>(chemotherapy, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Get Chemotherapy details by id and name
     *  -http://localhost:8080/api/chemotherapy/query?id=1&name=DAUNOrubicin
     * @param id
     * @param name
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<ChemotherapyDto> getChemotherapyByIdAndName(@RequestParam long id, @RequestParam String name) {
        ChemotherapyDto chemotherapy = chemotherapyService.getChemotherapyById(id);
        return ResponseEntity.ok(chemotherapy);
    }

    /**
     * Build Get All chemotherapy details REST API
     * http://localhost:8080/api/chemotherapy
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ChemotherapyDto>> getAllChemotherapyInfo() {
        List<ChemotherapyDto> chemotherapyList = chemotherapyService.getAllChemotherapyInfo();
        return new ResponseEntity<>(chemotherapyList, HttpStatus.OK);
    }

    /**
     * build create Chemotherapy Details REST API
     * @param chemotherapy
     * @return
     */
    @PostMapping
    public ResponseEntity<ChemotherapyDto> createChemotherapy(@Valid @RequestBody ChemotherapyDto chemotherapy) {
        ChemotherapyDto savedChemotherapy = chemotherapyService.createChemotherapy(chemotherapy);
        return new ResponseEntity<>(savedChemotherapy, HttpStatus.CREATED);
    }

    /**
     *  Build Update chemotherapy REST API
     *  http://localhost:8080/api/chemotherapy/1
     * @param chemotherapyName
     * @param chemotherapy
     * @return
     */
    @PutMapping("{name}")
    public ResponseEntity<ChemotherapyDto> updateChemotherapyDetails(@PathVariable("name") String chemotherapyName,
                                                                     @RequestBody @Valid ChemotherapyDto chemotherapy) {
        chemotherapy.setName(chemotherapyName);
        ChemotherapyDto updatedChemotherapy = chemotherapyService.updateChemotherapyDetails(chemotherapy);
        return new ResponseEntity<>(updatedChemotherapy, HttpStatus.OK);
    }

    /**
     * Build delete chemotherapy REST API
     * http://localhost:8080/api/chemotherapy/DAUNOrubicin
     * @param chemotherapyName
     * @return
     */
    @DeleteMapping("{name}")
    public ResponseEntity<String> deleteChemotherapy(@PathVariable("name") String chemotherapyName) {
        chemotherapyService.deleteChemotherapy(chemotherapyName);
        return new ResponseEntity<>(""+ chemotherapyName +" successfully deleted", HttpStatus.OK);
    }

}
