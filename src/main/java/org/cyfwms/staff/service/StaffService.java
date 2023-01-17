package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.StaffContactInformationDto;
import org.cyfwms.staff.dto.StaffDto;

public interface StaffService {
    StaffDto saveStaff(StaffDto staffDto);

    StaffDto readStaff(Long staffId);

    void removeStaff(Long employeeId);

}
