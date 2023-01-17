package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.StaffMedicalAndEmergencyDto;

public interface StaffMedicalAndEmergencyService {
    StaffMedicalAndEmergencyDto saveStaffMedicalAndEmergency(StaffMedicalAndEmergencyDto medicalAndEmergencyDto);

    StaffMedicalAndEmergencyDto readStaffMedicalAndEmergency(Long staffId);
}
