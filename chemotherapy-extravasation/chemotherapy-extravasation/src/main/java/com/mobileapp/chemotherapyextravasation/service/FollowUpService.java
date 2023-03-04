package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.FollowUpDto;

public interface FollowUpService {
    public FollowUpDto getFollowUpByAppointmentId(Long appointmentId);
    public FollowUpDto createFollowUpByAppointmentId(FollowUpDto followUpDto);
    public FollowUpDto updatePatientFollowUpByAppointmentId(Long appointmentId, FollowUpDto followUpDto);
    public FollowUpDto updateNurseFollowUpByAppointmentId(Long appointmentId, FollowUpDto followUpDto);
}
