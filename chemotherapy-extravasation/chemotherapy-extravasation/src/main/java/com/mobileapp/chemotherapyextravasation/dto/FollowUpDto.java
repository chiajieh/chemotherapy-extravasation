package com.mobileapp.chemotherapyextravasation.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowUpDto {

    private Long id;

    @NotNull(message = "Follow up date taken should not be null or empty.")
    private LocalDate dateTaken;

    @NotNull(message = "Follow up review status should not be null or empty.")
    private String reviewStatus;

    private String additionalInfo;

    @NotNull(message = "Follow up close up photo should not be null or empty.")
    private byte[] closeUpPhoto;

    @NotNull(message = "Follow up body part photo should not be null or empty.")
    private byte[] bodyPartPhoto;

    private String actionRequired;

    @NotNull(message = "Follow up appointment id should not be null or empty.")
    private Long appointmentId;
}
