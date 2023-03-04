package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.NurseDto;

import java.util.List;

public interface NurseService {
    public NurseDto createNurse(NurseDto nurseDto);
    public NurseDto getNurseById(long nurseId);
    public List<NurseDto> getAllNurse();
    public NurseDto updateNurse(NurseDto nurse);
    public void deleteNurse(long nurseId);
}
