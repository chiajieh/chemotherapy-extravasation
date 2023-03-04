package com.mobileapp.chemotherapyextravasation.dto;

import com.mobileapp.chemotherapyextravasation.entity.Appointment;
import com.mobileapp.chemotherapyextravasation.entity.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * To handle transfer of object from server to client
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NurseDto {
    private Long id;
    @NotEmpty(message = "Nurse first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "Nurse last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "Nurse username should not be null or empty")
    private String username;

    @NotEmpty(message = "Nurse email should not be null or empty")
    @Email(message = "email address should be valid")
    private String email;

    private List<Patient> patients;

    private List<Appointment> appointments;


}