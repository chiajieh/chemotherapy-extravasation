package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.ChemotherapyDto;

import java.util.List;

public interface ChemotherapyService {
    public ChemotherapyDto getChemotherapyDetailsByName(String name);
    ChemotherapyDto getChemotherapyById(long id);
    ChemotherapyDto createChemotherapy(ChemotherapyDto chemotherapyDto);
    List<ChemotherapyDto> getAllChemotherapyInfo();
    ChemotherapyDto updateChemotherapyDetails(ChemotherapyDto chemotherapy);
    void deleteChemotherapy(String chemotherapyName);
}
