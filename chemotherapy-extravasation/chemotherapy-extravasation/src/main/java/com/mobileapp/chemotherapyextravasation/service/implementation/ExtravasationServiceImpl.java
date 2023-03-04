package com.mobileapp.chemotherapyextravasation.service.implementation;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapp.chemotherapyextravasation.dto.ExtravasationDto;
import com.mobileapp.chemotherapyextravasation.entity.Extravasation;
import com.mobileapp.chemotherapyextravasation.entity.Patient;
import com.mobileapp.chemotherapyextravasation.exception.ChemotherapyExtravasationAPIException;
import com.mobileapp.chemotherapyextravasation.exception.ResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.repository.ExtravasationRepository;
import com.mobileapp.chemotherapyextravasation.repository.PatientRepository;
import com.mobileapp.chemotherapyextravasation.service.ExtravasationService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExtravasationServiceImpl implements ExtravasationService {

    @Autowired
    private ExtravasationRepository extravasationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get a extravasation by patient id
     * @param patientId
     * @return
     */
	public ExtravasationDto getExtravasationByPatientId(Long patientId) {

		Optional<Extravasation> extravasation = extravasationRepository.findByPatientId(patientId);
        if (!extravasation.isPresent()) {
            throw new ResourceNotFoundException("Extravasation", "patientId", patientId);
        }
        return modelMapper.map(extravasation.get(), ExtravasationDto.class);

	}

    /**
     * Get a extravasation by id
     * @param id
     * @return
     */
    // public ExtravasationDto getExtravasationById(Long id) {

    //     Optional<Extravasation> extravasation = extravasationRepository.findById(id);
    //     if (!extravasation.isPresent()) {
    //         throw new ResourceNotFoundException("Extravasation", "id", id);
    //     }
    //     return modelMapper.map(extravasation.get(), ExtravasationDto.class);

    // }


    /**
     * Create a new extravasation
     * @param extravasationDto
     * @return
     */
    public ExtravasationDto createExtravasation(ExtravasationDto extravasationDto) {

        Optional<Extravasation> optionalExtravasation = extravasationRepository.findByPatientId(extravasationDto.getPatientId());

        // Check if the extravasation exists for the patient id
        if (optionalExtravasation.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Patient " + 
                extravasationDto.getPatientId() + 
                "already has extravasation."
            ); 
        }

        // Check if the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(extravasationDto.getPatientId());
        if (!optionalPatient.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Patient " + extravasationDto.getPatientId() + " does not exist."); 
        }

        Extravasation extravasation = modelMapper.map(extravasationDto, Extravasation.class);
        // Set the patient on the extravasation to create the relationship
        extravasation.setPatient(optionalPatient.get());

        Extravasation savedExtravasation = extravasationRepository.save(extravasation);
        return modelMapper.map(savedExtravasation, ExtravasationDto.class);

    }

    /**
     * Update an existing extravasation by patient id
     * @param patientId
     * @param extravasationDto
     * @return
     */
    public ExtravasationDto updateExtravasationByPatientId(Long patientId, ExtravasationDto extravasationDto) {
        
        // Check if the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (!optionalPatient.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Patient " + patientId + " does not exist.");
        }

        // Check if the extravasation exists
        Optional<Extravasation> optionalExtravasation = extravasationRepository.findByPatientId(patientId);
        if (!optionalExtravasation.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Extravasation for patient " + patientId + " does not exist");
        }
        // Get the extravasation from the database
        Extravasation extravasationInDb = optionalExtravasation.get();

        // Update the extravasationInDb
        extravasationInDb.setDateTaken(extravasationDto.getDateTaken());
        extravasationInDb.setCloseUpPhoto(extravasationDto.getCloseUpPhoto());
        extravasationInDb.setBodyPartPhoto(extravasationDto.getBodyPartPhoto());
        extravasationInDb.setSize(extravasationDto.getSize());
        extravasationInDb.setIvSite(extravasationDto.getIvSite());
        extravasationInDb.setPlasticSurgeryRequired(extravasationDto.isPlasticSurgeryRequired());
        extravasationInDb.setGrade(extravasationDto.getGrade());
        
        // Save updated extravasation
        extravasationInDb = extravasationRepository.save(extravasationInDb);
        return modelMapper.map(extravasationInDb, ExtravasationDto.class);
    }

    /**
     * Update an existing extravasation by id
     */
    // public ExtravasationDto updateExtravasationById(Long id, ExtravasationDto extravasationDto) {
    //     // Check if the patient exists
    //     Optional<Patient> optionalPatient = patientRepository.findById(extravasationDto.getPatientId());
    //     if (!optionalPatient.isPresent()) {
    //         throw new ChemotherapyExtravasationAPIException("Patient " + extravasationDto.getPatientId() + " does not exist.");
    //     }

    //     Extravasation extravasation = modelMapper.map(extravasationDto, Extravasation.class);
    //     extravasation = extravasationRepository.save(extravasation);
    //     return modelMapper.map(extravasation, ExtravasationDto.class);
    // }

    /**
     * Delete an existing extravasation by patient id
     * @param patientId
     */
    @Transactional
    public void deleteExtravasationByPatientId(Long patientId) {

        // Check if the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (!optionalPatient.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Patient " + patientId + " does not exist.");
        }

        // Check if the extravasation exists
        Optional<Extravasation> optionalExtravasation = extravasationRepository.findByPatientId(patientId);
        if (!optionalExtravasation.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Extravasation for patient " + patientId + " does not exist");
        }

        // Delete the extravasation
        extravasationRepository.deleteByPatientId(patientId);
    }

    /**
     * Delete an existing extravasation by id
     */
    // public void deleteExtravasationById(Long id) {

    //     // Check if the extravasation exists
    //     Optional<Extravasation> optionalExtravasation = extravasationRepository.findById(id);
    //     if (!optionalExtravasation.isPresent()) {
    //         if (!optionalExtravasation.isPresent()) {
    //             throw new ChemotherapyExtravasationAPIException("Extravasation " + id + " does not exist");
    //         }
    //     }

    //     // Delete the extravasation
    //     extravasationRepository.deleteById(id);
    // }

}
