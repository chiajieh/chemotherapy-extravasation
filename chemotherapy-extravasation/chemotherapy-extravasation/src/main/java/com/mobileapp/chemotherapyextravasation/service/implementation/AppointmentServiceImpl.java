package com.mobileapp.chemotherapyextravasation.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapp.chemotherapyextravasation.dto.AppointmentDto;
import com.mobileapp.chemotherapyextravasation.entity.Appointment;
import com.mobileapp.chemotherapyextravasation.entity.Nurse;
import com.mobileapp.chemotherapyextravasation.entity.Patient;
import com.mobileapp.chemotherapyextravasation.exception.ChemotherapyExtravasationAPIException;
import com.mobileapp.chemotherapyextravasation.exception.ResourceNotFoundException;
import com.mobileapp.chemotherapyextravasation.repository.AppointmentRepository;
import com.mobileapp.chemotherapyextravasation.repository.NurseRepository;
import com.mobileapp.chemotherapyextravasation.repository.PatientRepository;
import com.mobileapp.chemotherapyextravasation.service.AppointmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private NurseRepository nurseRepository;

    public List<AppointmentDto> getPatientAppointmentsByPatientId(Long patientId) {
        
        // Check if the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (!optionalPatient.isPresent()) {
            throw new ResourceNotFoundException("Patient", "id", patientId);
        }
        
        // Get the patients appointments
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);

        // Check if there are any appointments
        if (appointments.size() == 0) {
            throw new ChemotherapyExtravasationAPIException("Patient " + patientId + " does not have any appointments.");
        }

        List<AppointmentDto> appointmentsDto = new ArrayList<>();
        
        // Map the appointments to a Dto
        for (Appointment appointment: appointments) {
            appointmentsDto.add(modelMapper.map(appointment, AppointmentDto.class));
        }

        return appointmentsDto;
    }

    public List<AppointmentDto> createAppointments(AppointmentDto appointmentDto) {

        // Check the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(appointmentDto.getPatientId());
        if (!optionalPatient.isPresent()) {
            throw new ResourceNotFoundException("Patient", "id", appointmentDto.getPatientId());
        }

        // Check the nurse exists
        Optional<Nurse> optionalNurse = nurseRepository.findById(appointmentDto.getNurseId());
        if (!optionalNurse.isPresent()) {
            throw new ResourceNotFoundException("Nurse", "id", appointmentDto.getNurseId());
        }

        List<AppointmentDto> appointmentDtoList = new ArrayList<AppointmentDto>();

        // Map the appointments to an appointmentDto
        // Create the first appointment 24 hours from the LocalDate in appointmentDto
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        appointment.setAppointmentDate(appointment.getAppointmentDate().plusDays(1));
        appointmentRepository.save(appointment);
        appointmentDtoList.add(modelMapper.map(appointment, AppointmentDto.class));

        // Create 6 more appointments every two weeks from the LocalDate in appointmentDto
        for (int i=0; i<7; i++) {
            appointment = new Appointment();
            appointment.setNurse(optionalNurse.get());
            appointment.setPatient(optionalPatient.get());
            appointment.setAppointmentDate(appointmentDto.getAppointmentDate().plusWeeks( (i+1) * 2));
            appointmentRepository.save(appointment);
            appointmentDtoList.add(modelMapper.map(appointment, AppointmentDto.class));
        }

        return appointmentDtoList;
    }

    public AppointmentDto updateAppointmentById(Long appointmentId, @Valid AppointmentDto appointmentDto) {

        // Check that the patient exists
        Optional<Patient> optionalPatient = patientRepository.findById(appointmentDto.getPatientId());
        if (!optionalPatient.isPresent()) {
            throw new ResourceNotFoundException("Patient", "id", appointmentDto.getPatientId());
        }

        // Check that the nurse exists
        Optional<Nurse> optionalNurse = nurseRepository.findById(appointmentDto.getNurseId());
        if (!optionalNurse.isPresent()) {
            throw new ResourceNotFoundException("Nurse", "id", appointmentDto.getNurseId());
        }

        // Check that the appointment exists
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (!optionalAppointment.isPresent()) {
            throw new ResourceNotFoundException("Appointment", "id", appointmentId);
        }

        // Get the appointment entity from the database
        Appointment appointment = optionalAppointment.get();

        // Set the attributes of the appointment entity from the database with those in the Dto
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setNurse(optionalNurse.get());
        appointment.setPatient(optionalPatient.get());

        // Save the appointment entity
        appointmentRepository.save(appointment);

        // Map the appointment entity into a appointmentDto and return it
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    public void deleteAppointmentById(Long appointmentId) {
        
        // Check that the appointment exists
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (!optionalAppointment.isPresent()) {
            throw new ResourceNotFoundException("Appointment", "id", appointmentId);
        }
        
        // Delete the appointment
        appointmentRepository.deleteById(appointmentId);
    }

}
