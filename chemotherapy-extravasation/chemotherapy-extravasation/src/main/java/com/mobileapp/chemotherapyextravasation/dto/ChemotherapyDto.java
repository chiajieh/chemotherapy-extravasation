package com.mobileapp.chemotherapyextravasation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * To handle transfer of object from server to client
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChemotherapyDto {
    private Long id;
    @NotEmpty(message = "Chemotherapy name should not be null or empty")
    private String name;
    @NotEmpty(message = "Chemotherapy type should not be null or empty")
    private String type;
    private String properties;
    private String antidote;
    private String intervention;
}