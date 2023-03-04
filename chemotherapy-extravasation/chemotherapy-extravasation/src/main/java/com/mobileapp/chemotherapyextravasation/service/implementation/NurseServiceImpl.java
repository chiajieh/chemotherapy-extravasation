package com.mobileapp.chemotherapyextravasation.service.implementation;

import com.mobileapp.chemotherapyextravasation.dto.NurseDto;
import com.mobileapp.chemotherapyextravasation.entity.Nurse;
import com.mobileapp.chemotherapyextravasation.exception.EmailAlreadyExistsException;
import com.mobileapp.chemotherapyextravasation.exception.ResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.repository.NurseRepository;
import com.mobileapp.chemotherapyextravasation.service.NurseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NurseServiceImpl implements NurseService {
    private NurseRepository nurseRepository;
    private ModelMapper modelMapper;

    /**
     * Method to create a new nurse entity
     * @param nurseDto
     * @return
     */
    @Override
    public NurseDto createNurse(NurseDto nurseDto) {
        Nurse nurse = modelMapper.map(nurseDto, Nurse.class);
        Optional<Nurse> optionalNurse = nurseRepository.findByEmail(nurseDto.getEmail());

        if(optionalNurse.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for Nurse");
        }
        Nurse savedNurse = nurseRepository.save(nurse);
        NurseDto savedNurseDto = modelMapper.map(savedNurse, NurseDto.class);
        return savedNurseDto;
    }

    /**
     * Method to get a nurse's details by ID
     * @param nurseId
     * @return
     */
    @Override
    public NurseDto getNurseById(long nurseId) {
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(() -> new ResourceNotFoundException("Nurse", "id", nurseId));
        return modelMapper.map(nurse, NurseDto.class);
    }

    /**
     * Method to get all the nurses
     * @return
     */
    @Override
    public List<NurseDto> getAllNurse() {
        List<Nurse> nurses = nurseRepository.findAll();
        return nurses.stream().map((nurse) -> modelMapper.map(nurse, NurseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to update a nurse's details
     * @param nurse
     * @return
     */
    @Override
    public NurseDto updateNurse(NurseDto nurse) {
        Nurse existingNurse = nurseRepository.findById(nurse.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Nurse", "id", nurse.getId()));
        existingNurse.setFirstName(nurse.getFirstName());
        existingNurse.setLastName(nurse.getLastName());
        existingNurse.setUsername(nurse.getUsername());
        existingNurse.setEmail(nurse.getEmail());
        Nurse updatedNurse = nurseRepository.save(existingNurse);
        return modelMapper.map(updatedNurse, NurseDto.class);

    }

    /**
     * Method to delete a nurse entity
     * @param nurseId
     */
    @Override
    public void deleteNurse(long nurseId) {
        Nurse existingNurse = nurseRepository.findById(nurseId).orElseThrow(
                () -> new ResourceNotFoundException("Nurse", "id", nurseId));
        nurseRepository.deleteById(nurseId);
    }

}
