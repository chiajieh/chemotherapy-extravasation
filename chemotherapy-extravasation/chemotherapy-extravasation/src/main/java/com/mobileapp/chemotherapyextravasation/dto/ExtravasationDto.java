package com.mobileapp.chemotherapyextravasation.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class ExtravasationDto {

    private Long id;

    @NotEmpty(message = "Extravasation date taken should not be null or empty")
    private LocalDate dateTaken;

    @NotEmpty(message = "Extravasation close up photo should not be null or empty")
    private byte[] closeUpPhoto;

    @NotEmpty(message = "Extravasation body part photo should not be null or empty")
    private byte[] bodyPartPhoto;

    @NotEmpty(message = "Extravasation size should not be null or empty")
    private double size;

    @NotEmpty(message = "Extravasation iv site should not be null or empty")
    private String ivSite;

    @NotEmpty(message = "Extravasation plastic surgery required should not be null or empty")
    private boolean plasticSurgeryRequired;

    @NotEmpty(message = "Extravasation grade should not be null or empty")
    private String grade;

    @NotEmpty(message = "Extravasation patient id should not be null or empty")
    private Long patientId;
}
