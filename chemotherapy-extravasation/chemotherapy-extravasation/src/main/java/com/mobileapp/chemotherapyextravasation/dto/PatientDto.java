package com.mobileapp.chemotherapyextravasation.dto;

import com.mobileapp.chemotherapyextravasation.entity.Appointment;
import com.mobileapp.chemotherapyextravasation.entity.Extravasation;
import com.mobileapp.chemotherapyextravasation.entity.Nurse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long id;
    @NotEmpty(message = "Patient's first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "Patient's last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "Patient's username should not be null or empty")
    private String username;

    @NotEmpty(message = "Patient's email should not be null or empty")
    @Email(message = "email address should be valid")
    private String email;

    private Nurse nurse;

    private List<Appointment> appointments;

    private Extravasation extravasation;


}