package com.mobileapp.chemotherapyextravasation.service;

import com.mobileapp.chemotherapyextravasation.dto.FollowUpDto;

public interface FollowUpService {
    public FollowUpDto getFollowUpByAppointmentId(Long appointmentId);
    public FollowUpDto createFollowUpByAppointmentId(FollowUpDto followUpDto);
}
