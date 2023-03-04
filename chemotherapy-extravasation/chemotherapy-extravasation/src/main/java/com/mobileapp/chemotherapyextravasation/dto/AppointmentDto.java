package com.mobileapp.chemotherapyextravasation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * To handle transfer of object from server to client
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Long id;

    @NotNull(message = "Appointment date should not be null or empty")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment nurse id should not be null or empty")
    private Long nurseId;

    @NotNull(message = "Appointment patient id should not be null or empty")
    private Long patientId;

    // private FollowUp followUp;
}