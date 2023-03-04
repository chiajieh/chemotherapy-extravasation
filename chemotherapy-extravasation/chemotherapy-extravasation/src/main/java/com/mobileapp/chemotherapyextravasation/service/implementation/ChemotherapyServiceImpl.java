package com.mobileapp.chemotherapyextravasation.service.implementation;

import com.mobileapp.chemotherapyextravasation.dto.ChemotherapyDto;
import com.mobileapp.chemotherapyextravasation.entity.Chemotherapy;
import com.mobileapp.chemotherapyextravasation.exception.ChemotherapyExtravasationAPIException;
import com.mobileapp.chemotherapyextravasation.exception.NameResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.exception.ResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.repository.ChemotherapyRepository;
import com.mobileapp.chemotherapyextravasation.service.ChemotherapyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChemotherapyServiceImpl implements ChemotherapyService {
    private ChemotherapyRepository chemotherapyRepository;
    private ModelMapper modelMapper;

    /**
     * Method to get Chemotherapy details by name
     * @param name
     * @return
     */
    @Override
    public ChemotherapyDto getChemotherapyDetailsByName(String name) {
        Chemotherapy chemotherapy = chemotherapyRepository.findByName(name).orElseThrow(() -> new NameResourceNotFoundException("Chemotherapy", "name", name));
        return modelMapper.map(chemotherapy, ChemotherapyDto.class);
    }

    /**
     * Method to get a chemotherapy details by ID
     * @param id
     * @return
     */
    @Override
    public ChemotherapyDto getChemotherapyById(long id) {
        Chemotherapy chemotherapy = chemotherapyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chemotherapy", "id", id));
        return modelMapper.map(chemotherapy, ChemotherapyDto.class);
    }

    /**
     * Method to create a new chemotherapy entity
     * @param chemotherapyDto
     * @return
     */
    @Override
    public ChemotherapyDto createChemotherapy(ChemotherapyDto chemotherapyDto) {
        Chemotherapy chemotherapy = modelMapper.map(chemotherapyDto, Chemotherapy.class);
        Optional<Chemotherapy> optionalChemotherapy = chemotherapyRepository.findByName(chemotherapyDto.getName());

        if(optionalChemotherapy.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Chemotherapy Name Already Exists");
        }
        Chemotherapy savedChemotherapy = chemotherapyRepository.save(chemotherapy);
        ChemotherapyDto savedChemotherapyDto = modelMapper.map(savedChemotherapy, ChemotherapyDto.class);
        return savedChemotherapyDto;
    }


    /**
     * Method to get all the chemotherapy details
     * @return
     */
    @Override
    public List<ChemotherapyDto> getAllChemotherapyInfo() {
        List<Chemotherapy> chemotherapies = chemotherapyRepository.findAll();
        return chemotherapies.stream().map((chemotherapy) -> modelMapper.map(chemotherapy, ChemotherapyDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to update a particular chemotherapy detail
     * NB: Name cannot be changed
     * @param chemotherapy
     * @return
     */
    @Override
    public ChemotherapyDto updateChemotherapyDetails(ChemotherapyDto chemotherapy) {
        Chemotherapy existingChemotherapy = chemotherapyRepository.findByName(chemotherapy.getName()).orElseThrow(
                () -> new NameResourceNotFoundException("Chemotherapy", "name", chemotherapy.getName()));
        existingChemotherapy.setType(chemotherapy.getType());
        existingChemotherapy.setProperties(chemotherapy.getProperties());
        existingChemotherapy.setAntidote(chemotherapy.getAntidote());
        existingChemotherapy.setIntervention(chemotherapy.getIntervention());

        Chemotherapy updatedChemotherapy = chemotherapyRepository.save(existingChemotherapy);
        return modelMapper.map(updatedChemotherapy, ChemotherapyDto.class);

    }

    /**
     * Method to delete a particular chemotherapy
     * @param chemotherapyName
     */
    @Override
    public void deleteChemotherapy(String chemotherapyName) {
        Chemotherapy existingChemotherapy = chemotherapyRepository.findByName(chemotherapyName).orElseThrow(
                () -> new NameResourceNotFoundException("Chemotherapy", "name", chemotherapyName));
        List<ChemotherapyDto> chemotherapies = getAllChemotherapyInfo();
        for (ChemotherapyDto chemotherapy : chemotherapies) {
            if (chemotherapy.getName().equals(chemotherapyName)) {
                long id = chemotherapy.getId();
                chemotherapyRepository.deleteById(id);
            }
        }
    }
}
