package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICReferralInfoDto;

public interface ICReferralInfoService {
    ICReferralInfoDto readAllReferralInfo(Long fileDetailsId);
    ICReferralInfoDto saveAllReferralInfo(ICReferralInfoDto initialContactReferralInfoDto);
}
