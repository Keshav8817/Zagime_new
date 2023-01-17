package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.StaffContactInformationDto;
import org.cyfwms.staff.dto.StaffJobAndBankingDto;

public interface StaffJobAndBankingService {
    StaffJobAndBankingDto saveStaffJobAndBanking(StaffJobAndBankingDto staffJobAndBankingDto);

    StaffJobAndBankingDto readStaffJobAndBanking(Long staffId);
}
