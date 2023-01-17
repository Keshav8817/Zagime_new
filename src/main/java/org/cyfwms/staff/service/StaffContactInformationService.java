package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.StaffContactInformationDto;
import org.cyfwms.staff.dto.StaffDto;

public interface StaffContactInformationService {
    StaffContactInformationDto saveContactInformation(StaffContactInformationDto contactInformationDto);

    StaffContactInformationDto readStaffContactInformation(Long staffId);
}
