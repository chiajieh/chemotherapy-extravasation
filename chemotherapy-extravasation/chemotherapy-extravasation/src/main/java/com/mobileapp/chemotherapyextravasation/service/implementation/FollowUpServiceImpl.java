package com.mobileapp.chemotherapyextravasation.service.implementation;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mobileapp.chemotherapyextravasation.dto.FollowUpDto;
import com.mobileapp.chemotherapyextravasation.entity.Appointment;
import com.mobileapp.chemotherapyextravasation.entity.FollowUp;
import com.mobileapp.chemotherapyextravasation.exception.ChemotherapyExtravasationAPIException;
import com.mobileapp.chemotherapyextravasation.exception.ResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.repository.AppointmentRepository;
import com.mobileapp.chemotherapyextravasation.repository.FollowUpRepository;
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
@Service
public class FollowUpServiceImpl implements FollowUpService {

    @Autowired
    private FollowUpRepository followUpRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FollowUpDto getFollowUpByAppointmentId(Long appointmentId) {

        // Check that the appointment exists
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (!optionalAppointment.isPresent()) {
            throw new ResourceNotFoundException("Appointment", "id", appointmentId);
        }

        // Check if the Follow Up exists
        Optional<FollowUp> optionalFollowUp = followUpRepository.findByAppointmentId(appointmentId);
        if (!optionalFollowUp.isPresent()) {
            throw new ResourceNotFoundException("FollowUp", "appointmentId", appointmentId);
        }

        FollowUp followUp = optionalFollowUp.get();
        // return the follow up DTO
        return modelMapper.map(followUp, FollowUpDto.class);
    }

    public FollowUpDto createFollowUpByAppointmentId(FollowUpDto followUpDto) {

        Long appointmentId = followUpDto.getAppointmentId();
        
        // Check that the appointment exists
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (!optionalAppointment.isPresent()) {
            throw new ResourceNotFoundException("Appointment", "id", appointmentId);
        }

        // CHeck if the follow up exists
        Optional<FollowUp> optionalFollowUp = followUpRepository.findByAppointmentId(appointmentId);
        if (optionalFollowUp.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Follow up with appointment id " + appointmentId + " already exists.");
        }

        // Create the follow up
        FollowUp followUp = modelMapper.map(followUpDto, FollowUp.class);
        followUpRepository.save(followUp);

        // Return the follow up dto
        return modelMapper.map(followUp, FollowUpDto.class);
    }

    @Override
    public FollowUpDto updatePatientFollowUpByAppointmentId(Long appointmentId, FollowUpDto followUpDto) {
        
        // Check that the appointment exists
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (!optionalAppointment.isPresent()) {
            throw new ResourceNotFoundException("Appointment", "id", appointmentId);
        }

        // CHeck if the follow up exists
        Optional<FollowUp> optionalFollowUp = followUpRepository.findByAppointmentId(appointmentId);
        if (optionalFollowUp.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Follow up with appointment id " + appointmentId + " already exists.");
        }

        // Get the follow up from database
        FollowUp followUpInDb = followUpRepository.findByAppointmentId(appointmentId).get();

        // Update body photo if it exists
        if (followUpDto.getBodyPartPhoto().length != 0) {
            followUpInDb.setBodyPartPhoto(followUpDto.getBodyPartPhoto());
            followUpInDb.setDateTaken(LocalDate.now());
        }

        // Update close up photo if it exists
        if (followUpDto.getCloseUpPhoto().length != 0) {
            followUpInDb.setCloseUpPhoto(followUpDto.getCloseUpPhoto());
            followUpInDb.setDateTaken(LocalDate.now());
        }

        // Save updated follow up
        followUpRepository.save(followUpInDb);

        // Map updated follow up to dto and return
        return modelMapper.map(followUpInDb, FollowUpDto.class);

    }

    @Override
    public FollowUpDto updateNurseFollowUpByAppointmentId(Long appointmentId, FollowUpDto followUpDto) {
        
        // Check that the appointment exists
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (!optionalAppointment.isPresent()) {
            throw new ResourceNotFoundException("Appointment", "id", appointmentId);
        }

        // CHeck if the follow up exists
        Optional<FollowUp> optionalFollowUp = followUpRepository.findByAppointmentId(appointmentId);
        if (optionalFollowUp.isPresent()) {
            throw new ChemotherapyExtravasationAPIException("Follow up with appointment id " + appointmentId + " already exists.");
        }

        // Get the follow up from database
        FollowUp followUpInDb = followUpRepository.findByAppointmentId(appointmentId).get();

        if (followUpDto.getReviewStatus() != null) {
            followUpInDb.setReviewStatus(followUpDto.getReviewStatus());
        }
        if (followUpDto.getAdditionalInfo() != null) {
            followUpInDb.setAdditionalInfo(followUpDto.getAdditionalInfo());
        }
        if (followUpDto.getActionRequired() != null) {
            followUpInDb.setActionRequired(followUpDto.getActionRequired());
        }

        // Save updated follow up
        followUpRepository.save(followUpInDb);

        // Map updated follow up to dto and return
        return modelMapper.map(followUpInDb, FollowUpDto.class);
    }
    

}
