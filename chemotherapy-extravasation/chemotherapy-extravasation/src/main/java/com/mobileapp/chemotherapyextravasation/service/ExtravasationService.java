package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.ExtravasationDto;

public interface ExtravasationService {
    public ExtravasationDto getExtravasationByPatientId(Long patientId);
    public ExtravasationDto createExtravasation(ExtravasationDto extravasationDto);
    public ExtravasationDto updateExtravasationByPatientId(Long patientId, ExtravasationDto extravasationDto);
    public void deleteExtravasationByPatientId(Long patientId);
}
